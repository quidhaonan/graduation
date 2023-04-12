import * as echarts from "echarts"
import { $post } from "../../utils/request"


// 加载采购热度数据
export async function initPurchasingHeat(purchasingHeat) {
    const option = {
        title: {
            // text: "采购热度",
            // subtext: "热度",
            left: "center",
        },
        tooltip: {
            trigger: "item",
        },
        legend: {
            orient: "vertical",
            left: "left",
        },
        series: [
            {
                name: "Access From",
                type: "pie",
                radius: "50%",
                data: [],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: "rgba(0, 0, 0, 0.5)",
                    },
                },
            },
        ],
    }

    // 加载数据
    await initData(option)

    const myChart = echarts.init(purchasingHeat)

    myChart.on('click',(event)=>{
        console.log(event)
    })

    option && myChart.setOption(option);
}

// 加载图表数据
export async function initData(option){
    // 获得采购热度的数据
    let heatCounts=await $post('getPurchasingHeatCounts')
    // 为配置赋值
    option.series[0].data=[]
    for(let i=0;i<heatCounts.length;i++){
        option.series[0].data.push({
            value:heatCounts[i].lastCounts,
            name:heatCounts[i].heatId+'级'
        })
    }
}