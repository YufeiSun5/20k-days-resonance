package com.resonance.core.ai.service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
/**
 * CN: skill 路由用 profile 打分，而不是把每个问题写成流程分支；新资料优先通过 skill 描述和少量别名扩展。
 * EN: Skill routing uses profile scoring instead of encoding every question as control flow; new knowledge should be added through skill descriptions and small alias sets first.
 * JP: skill ルーティングは分岐の積み増しではなく profile スコアで判断します。新しい知識はまず skill 説明と少数の別名で拡張します。
 */
public class SkillRouterService {

    private static final int MAX_ROUTED_SKILLS = 5;

    private static final Map<String, List<String>> PRODUCT_ALIASES = orderedMap(Map.of(
            "kingview-", List.of("组态王", "kingview", "king view", "kv"),
            "kingscada-", List.of("kingscada", "king scada", "scada", "ks", "ksp", "portal"),
            "kingscada-kh", List.of("kinghistorian", "king historian", "kh", "工业库", "rawbytime", "calculated", "samplingmode", "calculationmode")));

    private static final Map<String, List<String>> SKILL_ALIASES = orderedMap(Map.ofEntries(
            Map.entry("kingview-reports-recipes", List.of("报表", "配方", "rpt", "打印", "单元格", "单元", "recipe")),
            Map.entry("kingview-database", List.of("sql", "odbc", "数据库", "历史库", "sqlselect", "sqlconnect", "sqlinsert", "sqlupdate", "数据源", "查询")),
            Map.entry("kingview-scripting", List.of("命令语言", "脚本", "touchview", "运行系统", "函数", "事件")),
            Map.entry("kingview-curves-alarm", List.of("曲线", "趋势", "报警", "ht", "trend", "历史趋势")),
            Map.entry("kingview-variables", List.of("变量", "数据词典", "io变量", "i/o", "变量域", "变量名")),
            Map.entry("kingview-graphics", List.of("画面", "动画", "控件", "图库", "选择器", "日历", "datepicker", "datetimepicker", "activex", "属性", "方法")),
            Map.entry("kingscada-functions", List.of("函数", "命令语言", "sqlselect", "kdb", "varrefaddress", "kdbgetdataset", "kdbsqlexecutefromfile")),
            Map.entry("kingscada-curves-charts", List.of("曲线", "图表", "xy", "xychart", "棒图", "趋势", "trendpause", "trendresum")),
            Map.entry("kingscada-dataset", List.of("dataset", "数据集", "kdbcreatedataset", "kdbaddrow", "kdbsetcelldata")),
            Map.entry("kingscada-kh", List.of("kinghistorian", "kh", "history", "realtime", "rawbytime", "calculated", "samplingmode", "calculationmode", "工业库")),
            Map.entry("kingscada-scripting", List.of("脚本", "命令语言", "变量声明", "全局脚本", "局部脚本")),
            Map.entry("kingscada-ch02-project-designer", List.of("工程设计器", "工程管理", "工程属性", "项目配置", "开发环境")),
            Map.entry("kingscada-ch03-display-editor", List.of("画面编辑器", "画面", "图形绘制", "图层", "画面开发")),
            Map.entry("kingscada-ch04-symbols", List.of("精灵图", "图库", "图元", "符号", "组件复用")),
            Map.entry("kingscada-ch05-controls", List.of("控件", "activex", "表格", "列表", "按钮", "日期时间控件", "listview")),
            Map.entry("kingscada-ch06-security", List.of("用户安全", "权限", "角色", "登录", "安全管理")),
            Map.entry("kingscada-ch07-runtime", List.of("运行系统", "运行参数", "客户端运行", "运行环境", "调试")),
            Map.entry("kingscada-ch08-licensing", List.of("授权", "加密锁", "许可", "授权配置", "授权管理")),
            Map.entry("kingscada-ch09-variables", List.of("变量", "变量组", "io变量", "i/o", "系统变量", "变量属性")),
            Map.entry("kingscada-ch10-animation", List.of("动画连接", "动画", "颜色", "按钮颜色", "位置", "大小", "闪烁", "变量绑定")),
            Map.entry("kingscada-ch11-scripting", List.of("脚本", "命令语言", "事件脚本", "全局脚本", "脚本编辑器")),
            Map.entry("kingscada-ch12-alarm-event", List.of("报警", "事件", "报警组", "报警窗口", "确认", "报警记录", "事件日志")),
            Map.entry("kingscada-ch13-models", List.of("模型", "数据模型", "对象模型", "模板", "实例", "模型变量")),
            Map.entry("kingscada-ch14-curves-charts", List.of("曲线", "趋势", "历史趋势", "xy曲线", "图表", "棒图", "报表")),
            Map.entry("kingscada-ch15-reports", List.of("报表", "报表控件", "报表模板", "打印", "导出", "报表函数", "sql", "数据库", "查询")),
            Map.entry("kingscada-ch16-recipes", List.of("配方", "配方管理", "上传", "下载", "配方文件", "配方函数")),
            Map.entry("kingscada-ch17-data-access", List.of("外部程序", "访问scada数据", "opc", "odbc", "api", "数据接口")),
            Map.entry("kingscada-ch18-database", List.of("数据库", "关系库", "sql", "odbc", "查询", "插入", "更新", "数据库函数")),
            Map.entry("kingscada-ch19-network", List.of("网络", "客户端", "服务器", "通信", "分布式")),
            Map.entry("kingscada-ch20-redundancy", List.of("冗余", "热备", "主备切换", "冗余服务器", "容错")),
            Map.entry("kingscada-ch21-resources-i18n", List.of("资源管理", "国际化", "多语言", "语言切换", "资源文件")),
            Map.entry("kingscada-ch22-client-web", List.of("客户端打包", "web化", "web发布", "网页发布", "发布失败", "iis", "activex", "部署")),
            Map.entry("kingscada-ch23-history", List.of("历史记录", "历史数据", "归档", "采样", "历史查询")),
            Map.entry("kingscada-ch24-info-window", List.of("信息窗口", "运行日志", "调试输出", "系统信息", "消息显示")),
            Map.entry("kingscada-ch26-app-run-manager", List.of("应用运行管理器", "启动", "停止", "运行管理", "服务管理")),
            Map.entry("kingscada-ch27-remote-deploy", List.of("远程部署", "部署工具", "更新", "发布", "客户端部署")),
            Map.entry("kingscada-ch28-slb", List.of("负载均衡", "kingscadaslb", "服务均衡", "客户端访问")),
            Map.entry("kingscada-ch29-gis", List.of("gis", "地图", "地理信息", "地图控件", "空间数据")),
            Map.entry("kingscada-ch30-portal", List.of("portal", "瘦客户端", "浏览器访问", "现代浏览器", "门户系统", "web客户端", "ksp")),
            Map.entry("kingscada-command-functions", List.of("命令语言函数", "函数速查", "函数参数", "返回值")),
            Map.entry("kingscada-history-export", List.of("历史库导出", "历史数据导出", "导出工具", "归档导出")),
            Map.entry("kingscada-appendix-faq", List.of("常见问题", "故障排查", "错误处理", "问题解决", "失败", "不生效", "问号"))));

    public List<String> route(String query, List<String> requestedSkillNames, List<String> availableSkillNames) {
        Set<String> available = normalizeSet(availableSkillNames);
        Set<String> requested = normalizeSet(requestedSkillNames);
        Set<String> explicit = explicitlyMentioned(query, available);

        if (!explicit.isEmpty()) {
            explicit.retainAll(available);
            return List.copyOf(explicit);
        }
        if (!requested.isEmpty() && requested.size() < available.size()) {
            requested.retainAll(available);
            return List.copyOf(requested);
        }

        String text = normalizeText(query);
        Set<String> activeProductProfiles = activeProductProfiles(text);
        return available.stream()
                .filter(skillName -> !"skills".equals(skillName))
                .map(skillName -> new SkillScore(skillName, score(skillName, text, activeProductProfiles)))
                .filter(score -> score.value() > 0)
                .sorted(Comparator.comparingInt(SkillScore::value).reversed())
                .limit(MAX_ROUTED_SKILLS)
                .map(SkillScore::skillName)
                .toList();
    }

    private int score(String skillName, String text, Set<String> activeProductProfiles) {
        if (!activeProductProfiles.isEmpty() && activeProductProfiles.stream().noneMatch(product -> matchesProduct(skillName, product))) {
            return 0;
        }

        int directScore = 0;
        directScore += scoreTerms(text, splitSkillName(skillName), 8);
        directScore += scoreTerms(text, SKILL_ALIASES.getOrDefault(skillName, List.of()), 20);
        if (directScore == 0) {
            return 0;
        }

        int score = directScore;
        for (Map.Entry<String, List<String>> entry : PRODUCT_ALIASES.entrySet()) {
            if (matchesProduct(skillName, entry.getKey())) {
                score += scoreTerms(text, entry.getValue(), 10);
            }
        }
        return score;
    }

    private Set<String> activeProductProfiles(String text) {
        LinkedHashSet<String> result = new LinkedHashSet<>();
        for (Map.Entry<String, List<String>> entry : PRODUCT_ALIASES.entrySet()) {
            if (scoreTerms(text, entry.getValue(), 1) > 0) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    private boolean matchesProduct(String skillName, String productProfile) {
        return skillName.startsWith(productProfile) || skillName.equals(productProfile);
    }

    private int scoreTerms(String text, List<String> terms, int weight) {
        int score = 0;
        for (String term : terms) {
            if (text.contains(term.toLowerCase(Locale.ROOT))) {
                score += weight;
            }
        }
        return score;
    }

    private List<String> splitSkillName(String skillName) {
        return List.of(skillName.toLowerCase(Locale.ROOT).split("[-_]+")).stream()
                .filter(term -> !"kingview".equals(term))
                .filter(term -> !"kingscada".equals(term))
                .toList();
    }

    private Set<String> explicitlyMentioned(String query, Set<String> available) {
        String text = normalizeText(query);
        LinkedHashSet<String> result = new LinkedHashSet<>();
        for (String skillName : available) {
            if (text.contains(skillName)) {
                result.add(skillName);
            }
        }
        return result;
    }

    private Set<String> normalizeSet(List<String> values) {
        LinkedHashSet<String> result = new LinkedHashSet<>();
        if (values == null) {
            return result;
        }
        values.stream()
                .filter(value -> value != null && !value.isBlank())
                .map(value -> value.trim().toLowerCase(Locale.ROOT))
                .forEach(result::add);
        return result;
    }

    private String normalizeText(String query) {
        return query == null ? "" : query.toLowerCase(Locale.ROOT);
    }

    private static Map<String, List<String>> orderedMap(Map<String, List<String>> source) {
        return source.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), LinkedHashMap::putAll);
    }

    private record SkillScore(String skillName, int value) {
    }
}
