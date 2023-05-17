import * as echarts from 'echarts'


export async function initTitle(titleDom) {
    const htmlFontSize = parseInt(window.getComputedStyle(document.documentElement).fontSize, 10);
    const fontSize = htmlFontSize * 0.475; // 1.25 倍的根元素字体大小
    const myChart = echarts.init(titleDom)

    const option = {
        graphic: {
            elements: [
                {
                    type: 'text',
                    left: 'center',
                    top: 'center',
                    style: {
                        text: '惠农网农产品交易数据',
                        // fontSize: 20,
                        fontSize: fontSize,
                        // fontWeight: 'bold',
                        lineDash: [0, 200],
                        lineDashOffset: 0,
                        fill: 'transparent',
                        stroke: '#fff',
                        lineWidth: 1
                    },
                    keyframeAnimation: {
                        duration: 3000,
                        loop: false,
                        keyframes: [
                            {
                                percent: 0.7,
                                style: {
                                    fill: 'transparent',
                                    lineDashOffset: 200,
                                    lineDash: [200, 0]
                                }
                            },
                            {
                                // Stop for a while.
                                percent: 0.8,
                                style: {
                                    fill: 'transparent'
                                }
                            },
                            {
                                percent: 1,
                                style: {
                                    fill: 'white'
                                }
                            }
                        ]
                    }
                }
            ]
        }
    }
    option && myChart.setOption(option)
}