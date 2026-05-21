package com.resonance.core.ai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.resonance.core.ai.AiProperties;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SkillIndexServiceTest {

    @TempDir
    private Path tempDir;

    @Test
    void searchReturnsMatchingMarkdownChunks() throws Exception {
        Path skillDir = tempDir.resolve("kingscada-kh");
        Files.createDirectories(skillDir.resolve("references"));
        Files.writeString(skillDir.resolve("SKILL.md"), "# KingHistorian\nSamplingMode controls History query behavior.");
        Files.writeString(skillDir.resolve("references").resolve("demo.md"), "# SQL\nDataQuality = 192 means good quality.");
        Path kingviewDir = tempDir.resolve("kingview-database");
        Files.createDirectories(kingviewDir);
        Files.writeString(kingviewDir.resolve("SKILL.md"), "# 组态王数据库\nSQLSelect returns the first matching record.");

        AiProperties properties = new AiProperties();
        properties.setSkillsRoot(tempDir.toString());
        SkillIndexService service = new SkillIndexService(properties);
        service.reload();

        List<SkillSource> sources = service.search("DataQuality 192 查询", List.of("kingscada-kh"), 3);

        assertFalse(sources.isEmpty());
        assertEquals("kingscada-kh", sources.getFirst().skillName());
        assertEquals(List.of("kingscada-kh", "kingview-database"), service.listSkillNames());
    }

    @Test
    void explicitSkillNameNarrowsSearchScope() throws Exception {
        Path kingScadaDir = tempDir.resolve("kingscada-functions");
        Files.createDirectories(kingScadaDir);
        Files.writeString(kingScadaDir.resolve("SKILL.md"), "# KingSCADA\nSQLSelectTop is a KingSCADA function.");
        Path kingviewDir = tempDir.resolve("kingview-database");
        Files.createDirectories(kingviewDir.resolve("references"));
        Files.writeString(kingviewDir.resolve("SKILL.md"), "# 组态王数据库\nSQLSelect selects records in KingView.");
        Files.writeString(
                kingviewDir.resolve("references").resolve("database.md"),
                "# 组态王SQL访问\n"
                        + "背景说明。".repeat(900)
                        + "SQLSelect(DeviceID, \"KingTable\", \"BIND2\", \"\", \"\");\n"
                        + "该命令选择表格中的记录并返回第一条记录。");

        AiProperties properties = new AiProperties();
        properties.setSkillsRoot(tempDir.toString());
        SkillIndexService service = new SkillIndexService(properties);
        service.reload();

        List<SkillSource> sources = service.search(
                "请只基于 kingview-database 说明 SQLSelect",
                List.of("kingscada-functions", "kingview-database"),
                3);

        assertFalse(sources.isEmpty());
        assertEquals("kingview-database", sources.getFirst().skillName());
        assertEquals("kingview-database/references/database.md", sources.getFirst().path());
        assertEquals(true, sources.getFirst().excerpt().contains("SQLSelect(DeviceID"));
    }

    @Test
    void dateTimePickerQuestionPrefersCalendarControlChunk() throws Exception {
        Path graphicsDir = tempDir.resolve("kingview-graphics");
        Files.createDirectories(graphicsDir.resolve("references"));
        Files.writeString(
                graphicsDir.resolve("references").resolve("graphics.md"),
                "# 14.3.4 KingTree控件\n"
                        + "KingTree控件 控件属性 控件方法。".repeat(120)
                        + "\n# 14.3.12 日历控件\n"
                        + "日历控件有两个组成：日期控件、时间控件。\n"
                        + "GetYear() 方法获取控件设定表示时间的年份。\n"
                        + "\\local\\tag1 = Ctrl1.GetYear();\n"
                        + "SelChangedata(FLOAT fYear,FLOAT fMonth,FLOAT fDay) 是日期选择变化事件。");

        AiProperties properties = new AiProperties();
        properties.setSkillsRoot(tempDir.toString());
        SkillIndexService service = new SkillIndexService(properties);
        service.reload();

        List<SkillSource> sources = service.search(
                "怎么获取kingview的时间选择器和日期选择器的值",
                List.of("kingview-graphics"),
                3);

        assertFalse(sources.isEmpty());
        assertEquals("14.3.12 日历控件", sources.getFirst().heading());
    }
}
