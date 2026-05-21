package com.resonance.core.ai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class SkillRouterServiceTest {

    private final SkillRouterService router = new SkillRouterService();
    private final List<String> available = List.of(
            "kingscada-functions",
            "kingscada-kh",
            "kingscada-dataset",
            "kingview-database",
            "kingview-scripting",
            "kingview-reports-recipes",
            "kingview-graphics",
            "kingview-variables",
            "kingscada-ch05-controls",
            "kingscada-ch12-alarm-event",
            "kingscada-ch15-reports",
            "kingscada-ch18-database",
            "kingscada-ch22-client-web",
            "kingscada-ch30-portal",
            "kingscada-appendix-faq");

    @Test
    void explicitSkillNameWinsOverBroadRequestList() {
        List<String> routed = router.route(
                "请只基于 kingview-database 说明 SQLSelect",
                available,
                available);

        assertEquals(List.of("kingview-database"), routed);
    }

    @Test
    void kingviewSqlQuestionRoutesToDatabaseFirst() {
        List<String> routed = router.route(
                "组态王 7.5 SQLSelect 怎么用",
                available,
                available);

        assertEquals(List.of("kingview-database"), routed);
    }

    @Test
    void historianQuestionRoutesToKhSources() {
        List<String> routed = router.route(
                "KingHistorian History 表 SamplingMode 怎么配置",
                available,
                available);

        assertEquals(List.of("kingscada-kh"), routed);
    }

    @Test
    void kingviewReportSqlRoutesReportBeforeDatabase() {
        List<String> routed = router.route(
                "组态王报表里用 SQL 查询并写入单元格，给完整代码",
                available,
                available);

        assertEquals(List.of("kingview-reports-recipes", "kingview-database"), routed);
    }

    @Test
    void kingviewDateTimePickerRoutesToGraphicsControls() {
        List<String> routed = router.route(
                "怎么获取kingview的时间选择器和日期选择器的值",
                available,
                available);

        assertEquals(List.of("kingview-graphics"), routed);
    }

    @Test
    void kingScadaControlQuestionRoutesToChapterSkill() {
        List<String> routed = router.route(
                "KingSCADA 控件里的 ListView 怎么绑定数据",
                available,
                available);

        assertEquals("kingscada-ch05-controls", routed.getFirst());
    }

    @Test
    void kingScadaReportSqlQuestionRoutesToReportAndDatabaseChapters() {
        List<String> routed = router.route(
                "KingSCADA 报表里用 SQL 查询数据库并导出",
                available,
                available);

        assertEquals(List.of("kingscada-ch15-reports", "kingscada-ch18-database"), routed.subList(0, 2));
    }

    @Test
    void kingScadaFunctionQuestionDoesNotRouteToKingViewFunction() {
        List<String> routed = router.route(
                "KingSCADA 里 SQLSelect 怎么用",
                available,
                available);

        assertEquals("kingscada-functions", routed.getFirst());
    }

    @Test
    void scadaWebFailureRoutesToClientWebAndFaq() {
        List<String> routed = router.route(
                "Scada网页发布失败",
                available,
                available);

        assertEquals("kingscada-ch22-client-web", routed.getFirst());
        assertEquals(true, routed.contains("kingscada-appendix-faq"));
    }
}
