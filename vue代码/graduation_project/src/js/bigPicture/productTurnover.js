import { $get } from '@/utils/request';
import * as echarts from 'echarts'


// 将数据定义在外面，便于 formatter 获取到
let productTurnover
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
            },
            formatter:function(params){
                let result=`
                            <table>
                                <tr>
                                    <td><h3>名称</h3></td>
                                    <td>：${productTurnover[params[0].dataIndex].proName}</td>
                                </tr>
                                <tr>
                                    <td><h3>营业额</h3></td>
                                    <td>：${productTurnover[params[0].dataIndex].turnover}元</td>
                                </tr>
                                <tr>
                                    <td><h3>价格</h3></td>
                                    <td>：${productTurnover[params[0].dataIndex].price}元</td>
                                </tr>
                                <tr>
                                    <td><h3>起批量</h3></td>
                                    <td>：${productTurnover[params[0].dataIndex].startBatching}</td>
                                </tr>
                                <tr>
                                    <td><h3>成交人数</h3></td>
                                    <td>：${productTurnover[params[0].dataIndex].traded}</td>
                                </tr>
                                <tr>
                                    <td><h3>收藏人数</h3></td>
                                    <td>：${productTurnover[params[0].dataIndex].collectorsCounts}</td>
                                </tr>
                            </table>
                            `
                return result
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
    productTurnover=await $get('/bigPicture/productTurnover')
    // 给配置赋值
    option.xAxis.data=[]
    option.series[0].data=[]
    for(let i=0;i<productTurnover.length;i++){
        option.xAxis.data.push(productTurnover[i].proName)
        option.series[0].data.push(productTurnover[i].turnover)
    }
}