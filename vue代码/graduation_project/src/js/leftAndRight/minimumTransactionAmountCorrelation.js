import * as echarts from "echarts"
import { $get } from "../../utils/request"


export async function initStartBatching(startBatchingDom) {
    var myChart = echarts.init(startBatchingDom);
    var option;
    // 使移动鼠标的时候获得此时 x 轴的位置
    let dimension

    // let data = await $post('/getFiveRelationship')
    let data = await $get('/leftAndRight/minimumTransactionAmountCorrelation')
    console.log(data)

    setTimeout(function () {
        option = {
            legend: {},
            tooltip: {
                trigger: "axis",
                showContent: true,
                formatter: function (params) {
                    // 这里是你需要自定义的提示内容的格式和逻辑
                    // 可以根据需要来展示数据和处理逻辑
                    // 下面是一个示例，展示了当前位置的x轴和每条线的对应数据
                    let result = `<table>
                                    <tr>
                                        <td><h3>最低交易金额</h3></td>
                                        <td>：${data[0][dimension]}元</td>
                                    </tr>
                                    <tr>
                                        <td><h3>处于该最低交易金额的总农产品数</h3></td>
                                        <td>：${data[5][dimension]}</td>
                                    </tr>`
                    for (let i = 0; i < params.length; i++) {
                        if (params[i].seriesName == '收藏人数') {
                            result += `<tr>
                                        <td><h3>收藏人数占比最大</h3></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>${data[6][dimension][0].name}</td>
                                        <td>：${data[6][dimension][0].value}人</td>
                                    </tr>`
                        } else if (params[i].seriesName == '询价人数') {
                            result += `<tr>
                                            <td><h3>询价人数占比最大</h3></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>${data[6][dimension][1].name}</td>
                                            <td>：${data[6][dimension][1].value}人</td>
                                        </tr>
                                        `
                        } else if (params[i].seriesName == '成交人数') {
                            result += `<tr>
                                            <td><h3>成交人数占比最大</h3></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>${data[6][dimension][2].name}</td>
                                            <td>：${data[6][dimension][2].value}人</td>
                                        </tr>
                                        `
                        } else if (params[i].seriesName == '评价人数') {
                            result += `<tr>
                                            <td><h3>询价人数占比最大</h3></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>${data[6][dimension][3].name}</td>
                                            <td>：${data[6][dimension][3].value}人</td>
                                        </tr>`
                        }
                    }
                    result += '</table>'
                    return result
                },
            },
            dataset: {
                source: [
                    // ["product", "2012", "2013", "2014", "2015", "2016", "2017"],
                    // ["Milk Tea", 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
                    // ["Matcha Latte", 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
                    // ["Cheese Cocoa", 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
                    // ["Walnut Brownie", 25.2, 37.1, 41.2, 18, 33.9, 49.1],
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    data[4],
                ],
            },
            xAxis: { type: "category" },
            yAxis: { gridIndex: 0 },
            grid: { top: "55%" },
            series: [
                {
                    type: "line",
                    smooth: true,
                    seriesLayoutBy: "row",
                    emphasis: { focus: "series" },
                },
                {
                    type: "line",
                    smooth: true,
                    seriesLayoutBy: "row",
                    emphasis: { focus: "series" },
                },
                {
                    type: "line",
                    smooth: true,
                    seriesLayoutBy: "row",
                    emphasis: { focus: "series" },
                    // 如果不设置，默认的黄色和红色区分不明显
                    color: '#9a60b4'
                },
                {
                    type: "line",
                    smooth: true,
                    seriesLayoutBy: "row",
                    emphasis: { focus: "series" },
                },
                {
                    type: "pie",
                    id: "pie",
                    radius: "30%",
                    center: ["50%", "25%"],
                    emphasis: {
                        focus: "self",
                    },
                    label: {
                        formatter: "{b}: {@3} ({d}%)",
                    },
                    encode: {
                        itemName: "最低交易金额",
                        value: '0.565',
                        tooltip: '0.565',
                    },
                },
            ],
        };
        myChart.on("updateAxisPointer", function (event) {
            const xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
                dimension = xAxisInfo.value + 1;
                myChart.setOption({
                    series: {
                        id: "pie",
                        label: {
                            formatter: "{b}: {@[" + dimension + "]} ({d}%)",
                            // formatter: "{b}",
                        },
                        encode: {
                            value: dimension,
                            tooltip: dimension,
                        },
                    },
                });
            }
        });
        myChart.setOption(option);
    });

    option && myChart.setOption(option);

    if (option && typeof option === "object") {
        myChart.setOption(option);
    }

    window.addEventListener("resize", myChart.resize);
}