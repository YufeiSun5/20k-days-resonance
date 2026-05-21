package com.resonance.core.ai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class SkillDependencyServiceTest {

    private final SkillDependencyService dependencyService = new SkillDependencyService();
    private final List<String> available = List.of(
            "kingview-reports-recipes",
            "kingview-database",
            "kingview-scripting",
            "kingview-curves-alarm",
            "kingview-graphics",
            "kingview-variables",
            "kingscada-curves-charts",
            "kingscada-dataset",
            "kingscada-kh",
            "kingscada-scripting",
            "kingscada-functions",
            "kingscada-ch05-controls",
            "kingscada-ch09-variables",
            "kingscada-ch11-scripting",
            "kingscada-ch15-reports",
            "kingscada-ch18-database",
            "kingscada-command-functions");

    @Test
    void reportSqlTaskAddsDatabaseAndScriptingSkills() {
        List<String> expanded = dependencyService.expand(
                "组态王报表里用 SQL 查询并写入单元格，给完整代码",
                List.of("kingview-reports-recipes"),
                available);

        assertEquals(List.of("kingview-reports-recipes", "kingview-scripting", "kingview-database"), expanded);
    }

    @Test
    void databaseTaskAddsScriptingSkill() {
        List<String> expanded = dependencyService.expand(
                "组态王 SQLSelect 怎么用",
                List.of("kingview-database"),
                available);

        assertEquals(List.of("kingview-database", "kingview-scripting"), expanded);
    }

    @Test
    void kingScadaHistoricalChartAddsDatasetAndKhSkills() {
        List<String> expanded = dependencyService.expand(
                "KingSCADA 历史曲线从 KH 查询数据",
                List.of("kingscada-curves-charts"),
                available);

        assertEquals(List.of("kingscada-curves-charts", "kingscada-dataset", "kingscada-kh", "kingscada-scripting", "kingscada-functions"), expanded);
    }

    @Test
    void kingHistorianTaskAddsDatasetSkill() {
        List<String> expanded = dependencyService.expand(
                "KingHistorian History 表 SamplingMode 怎么配置",
                List.of("kingscada-kh"),
                available);

        assertEquals(List.of("kingscada-kh", "kingscada-dataset", "kingscada-functions"), expanded);
    }

    @Test
    void controlValueTaskAddsScriptingAndVariableSkills() {
        List<String> expanded = dependencyService.expand(
                "怎么获取kingview的时间选择器和日期选择器的值",
                List.of("kingview-graphics"),
                available);

        assertEquals(List.of("kingview-graphics", "kingview-scripting", "kingview-variables"), expanded);
    }

    @Test
    void kingScadaControlTaskAddsScriptAndVariableChapters() {
        List<String> expanded = dependencyService.expand(
                "KingSCADA 控件里的 ListView 怎么绑定变量",
                List.of("kingscada-ch05-controls"),
                available);

        assertEquals(List.of("kingscada-ch05-controls", "kingscada-ch11-scripting", "kingscada-ch09-variables"), expanded);
    }

    @Test
    void kingScadaReportSqlTaskAddsDatabaseChapter() {
        List<String> expanded = dependencyService.expand(
                "KingSCADA 报表里用 SQL 查询数据库并导出",
                List.of("kingscada-ch15-reports"),
                available);

        assertEquals(List.of("kingscada-ch15-reports", "kingscada-ch11-scripting", "kingscada-command-functions", "kingscada-ch18-database"), expanded);
    }
}
