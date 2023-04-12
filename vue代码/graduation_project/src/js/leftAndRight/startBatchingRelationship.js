import * as echarts from "echarts"
import { $post } from "../../utils/request"

export async function initStartBatching(startBatchingDom) {
    var myChart = echarts.init(startBatchingDom);
    var option;

    let data = await $post('/getFiveRelationship')

    setTimeout(function () {
        option = {
            legend: {},
            tooltip: {
                trigger: "axis",
                showContent: false,
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
                        itemName: "起批量",
                        value: '3',
                        tooltip: '3',
                    },
                },
            ],
        };
        myChart.on("updateAxisPointer", function (event) {
            const xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
                const dimension = xAxisInfo.value + 1;
                console.log(dimension)
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