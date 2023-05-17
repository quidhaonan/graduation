import * as echarts from "echarts"
import { $get } from "../../utils/request"


export async function initTemperatureBand(temperatureBandDom) {
    let temperatureBand = await $get('/leftAndRight/temperatureBand')
    // x 轴显示数据
    let xAxisData = []
    // 蔬菜数据
    const vegetable = []
    // 农资农机数据
    const agriculturalMaterialsAndAgriculturalMachinery = []
    // 粮油米面数据
    const grainOilRiceAndNoodles = []
    // 种子种苗数据
    const seedSeedlings = []
    // 农副加工数据
    const agriculturalAndSidelineProcessing = []
    // 水产数据
    const aquatic = []
    // 水果数据
    const fruit = []
    // 禽畜肉蛋数据
    const poultryMeatEggs = []

    // 防止报 item 未定义的错误
    let item
    for (item in temperatureBand) {
        xAxisData.push(item)
        vegetable.push(temperatureBand[item][0].counts)
        agriculturalMaterialsAndAgriculturalMachinery.push(temperatureBand[item][1].counts)
        grainOilRiceAndNoodles.push(temperatureBand[item][2].counts)
        seedSeedlings.push(temperatureBand[item][3].counts)
        agriculturalAndSidelineProcessing.push(temperatureBand[item][4].counts)
        aquatic.push(temperatureBand[item][5].counts)
        fruit.push(temperatureBand[item][6].counts)
        poultryMeatEggs.push(temperatureBand[item][7].counts)
    }


    var dom = document.getElementById("temperatureBand");
    var myChart = echarts.init(dom, null, {
        renderer: "canvas",
        useDirtyRect: false,
    });
    var app = {};

    var option;

    option = {
        tooltip: {
            trigger: "axis",
            axisPointer: {
                type: "shadow",
            },
            formatter:function(params){
                const values=temperatureBand[params[0].axisValue]
                let result=`
                        <h3>${params[0].axisValue}</h3>
                        <table>
                            <tr>
                                <th>种类</th>
                                <th>&nbsp;&nbsp;&nbsp;</th>
                                <th>数量</th>
                                <th>&nbsp;&nbsp;&nbsp;</th>
                                <th>占比</th>
                            </tr>
                    `
                for(item of values){
                    let percent=(parseFloat(item['percent'])*100).toFixed(2)+'%'
                    result+=`
                            <tr>
                                <td>${item['levelName']}</td>
                                <td></td>
                                <td>${item['counts']}</td>
                                <td></td>
                                <td>${percent}</td>
                            </tr>
                        `
                }
                result+=`
                        </table>
                    `
                return result
            }
        },
        legend: {},
        grid: {
            left: "3%",
            right: "4%",
            bottom: "11%",
            containLabel: true,
        },
        xAxis: [
            {
                type: "category",
                data: xAxisData
            },
        ],
        yAxis: [
            {
                type: "value",
            },
        ],
        series: [
            {
                name: temperatureBand[xAxisData[0]][0].levelName,
                type: "bar",
                emphasis: {
                    focus: "series",
                },
                data: vegetable,
            },
            {
                name: temperatureBand[xAxisData[0]][1].levelName,
                type: "bar",
                stack: "Ad",
                emphasis: {
                    focus: "series",
                },
                data: agriculturalMaterialsAndAgriculturalMachinery,
            },
            {
                name: temperatureBand[xAxisData[0]][2].levelName,
                type: "bar",
                stack: "Ad",
                emphasis: {
                    focus: "series",
                },
                data: grainOilRiceAndNoodles,
            },
            {
                name: temperatureBand[xAxisData[0]][3].levelName,
                type: "bar",
                stack: "Ad",
                emphasis: {
                    focus: "series",
                },
                data: seedSeedlings,
            },
            {
                name: temperatureBand[xAxisData[0]][4].levelName,
                type: "bar",
                data: agriculturalAndSidelineProcessing,
                emphasis: {
                    focus: "series",
                },
                // markLine: {
                //   lineStyle: {
                //     type: "dashed",
                //   },
                //   data: [[{ type: "min" }, { type: "max" }]],
                // },
            },
            {
                name: temperatureBand[xAxisData[0]][5].levelName,
                type: "bar",
                barWidth: 5,
                stack: "Search Engine",
                emphasis: {
                    focus: "series",
                },
                data: aquatic,
            },
            {
                name: temperatureBand[xAxisData[0]][6].levelName,
                type: "bar",
                stack: "Search Engine",
                emphasis: {
                    focus: "series",
                },
                data: fruit,
            },
            {
                name: temperatureBand[xAxisData[0]][7].levelName,
                type: "bar",
                stack: "Search Engine",
                emphasis: {
                    focus: "series",
                },
                data: poultryMeatEggs,
            },
        ],
    };

    if (option && typeof option === "object") {
        myChart.setOption(option);
    }

    window.addEventListener("resize", myChart.resize);
}