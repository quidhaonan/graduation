import { $post } from '@/utils/request';
import * as echarts from 'echarts'

export async function initProductTurnover(productTurnoverDom) {
    const myChart = echarts.init(productTurnoverDom);

    
    const option = {
        title: {
            // text: '农产品销售额',
            left: 500
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: false
                },
                saveAsImage: {
                    pixelRatio: 2
                }
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            bottom: 90
        },
        dataZoom: [
            {
                type: 'inside'
            },
            {
                type: 'slider'
            }
        ],
        xAxis: {
            data: [],
            silent: false,
            splitLine: {
                show: false
            },
            splitArea: {
                show: false
            }
        },
        yAxis: {
            splitArea: {
                show: false
            }
        },
        series: [
            {
                type: 'bar',
                data: [],
                // Set `large` for large data amount
                large: true
            }
        ]
    };

    await initData(option)

    
    option && myChart.setOption(option);
}


async function initData(option){
    // 获取数据
    let productTurnover=await $post('/getProductTurnover')

    // 给配置赋值
    option.xAxis.data=[]
    option.series[0].data=[]
    for(let i=0;i<productTurnover.length;i++){
        option.xAxis.data.push(productTurnover[i].proName)
        option.series[0].data.push(productTurnover[i].turnover)
    }
}
