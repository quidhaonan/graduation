import * as echarts from "echarts"
import { $get } from "../../utils/request"


// 如果 initFirstLevel 调用 initRoseDiagram 的话，就获取不到显示玫瑰图的容器
// 因此打算在 initFirstLevel 的方法中给该属性赋值键盘事件，以便 initRoseDiagram 使用
let $event

// 加载第一第二级分类的数据
export async function initAllLevelTurnover(secondLevelCounts, autoClick) {
    let firstLevelTurnover = await $get('/leftAndRight/firstLevelTurnover')
    // const totalTurnover = firstLevelTurnover.reduce((sum, item) => sum + item.turnover, 0);
    // 存放第二级的占比
    let percents=[]

    let myChart = echarts.init(secondLevelCounts, null, {
        renderer: "canvas",
        useDirtyRect: false,
    });
    let app = {};

    let option;

    option = {
        xAxis: {
            data: ["蔬菜", "农资农机", "粮油米面", '种子种苗', '农副加工', '水产', '水果', '禽畜肉蛋'],
            // x 轴名称
            name: '农产品类别',
            // 名称出现位置
            nameLocation: 'middle',
            nameTextStyle: {
                // 使 x 轴的名字不至于重合
                padding: [14, 4, 5, 6]
            }
        },
        yAxis: {
            // y 轴名称
            // name: '农产品销售额',
            name: '农产品总营业额',
            nameTextStyle:{
                padding: [0, 0, 5, -50] 
            },

            // // 使 Y 轴出现单位（https://zhuanlan.zhihu.com/p/382691895）
            // type: "value",
            // // 看这里，看这里，看这里，看这里，看这里，看这里，看这里，看这里，看这里，看这里，
            // // 看这里，看这里，看这里，看这里
            // axisLabel: {
            //     //这种做法就是在y轴的数据的值旁边拼接单位，貌似也挺方便的
            //     formatter: "{value} 元",
            // },
        },
        grid: {
            // 解决数字被遮挡问题
            left: 80,
            // 使 Y 轴出现单位所连带改的设置
            // left: 95,
            top: 60,
            bottom: 40
        },
        dataGroupId: "",
        animationDurationUpdate: 500,
        series: {
            name: '农产品销售额',
            type: "bar",
            id: "sales",
            data: [
                {
                    value: firstLevelTurnover[0].turnover,
                    groupId: firstLevelTurnover[0].levelNo,
                    percent: firstLevelTurnover[0].percent
                },
                {
                    value: firstLevelTurnover[1].turnover,
                    groupId: firstLevelTurnover[1].levelNo,
                    percent: firstLevelTurnover[1].percent
                },
                {
                    value: firstLevelTurnover[2].turnover,
                    groupId: firstLevelTurnover[2].levelNo,
                    percent: firstLevelTurnover[2].percent
                },
                {
                    value: firstLevelTurnover[3].turnover,
                    groupId: firstLevelTurnover[3].levelNo,
                    percent: firstLevelTurnover[3].percent
                },
                {
                    value: firstLevelTurnover[4].turnover,
                    groupId: firstLevelTurnover[4].levelNo,
                    percent: firstLevelTurnover[4].percent
                },
                {
                    value: firstLevelTurnover[5].turnover,
                    groupId: firstLevelTurnover[5].levelNo,
                    percent: firstLevelTurnover[5].percent
                },
                {
                    value: firstLevelTurnover[6].turnover,
                    groupId: firstLevelTurnover[6].levelNo,
                    percent: firstLevelTurnover[6].percent
                },
                {
                    value: firstLevelTurnover[7].turnover,
                    groupId: firstLevelTurnover[7].levelNo,
                    percent: firstLevelTurnover[7].percent
                },
            ],
            universalTransition: {
                enabled: true,
                divideShape: "clone",
            },
        },
        textStyle: {
            // 字体斜体
            fontStyle: 'italic',
            // 字体族
            fontFamily: 'Microsoft YaHei'
        },
        // 加了这个后鼠标移入有动画了，在 series 添加的 name 有用了
        tooltip: {
            // 使一条竖杠变为柱状
            trigger: "axis",
            axisPointer: {
                // 添加阴影
                type: "shadow"
            },
            // {a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
            formatter: function (params) {
                var value = params[0].value;
                let percent

                // 根据是否有 percent 来判断是否切换获取占比的方式
                if(params[0].data.percent){
                    percent=params[0].data.percent+'%'
                }else{
                    // params[0].dataIndex，获取当前数据在 data 数组中的索引
                    percent=percents[params[0].dataIndex]+'%'
                }
                let result=`
                            <table>
                                <tr>
                                    <td>${params[0].seriesName}</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>${params[0].name}</td>
                                    <td>：${value}元</td>
                                </tr>
                                <tr>
                                    <td>占比</td>
                                    <td>：${percent}</td>
                                </tr>
                            </table>
                            `

                return result
            }

        },
        // 柱子颜色
        color: '#9a60b4',
    };
    // 原先有数据的，但是现在是通过动态的增加，因此置为空
    let drilldownData = []

    myChart.on("click", async function (event) {
        if (event.data) {

            // 自己加的
            // 判断是否有 groupId 这个值，来决定是否请求 ajax（最后一级的时候没有该属性）
            if (event.data.groupId) {
                // 第二层的数据向后台请求
                let secondLevelTurnover = await $get('/leftAndRight/secondLevelTurnover/'+event.data.groupId)
                // 存放第二级的占比情况
                percents=[]
                
                const secondLevelInfo = {
                    // 与第一级进行绑定的依据
                    dataGroupId: event.data.groupId,
                    data: []
                }
                // 通过请求过来的数据，动态的对第二级的内容进行添加
                for (let i = 0; i < secondLevelTurnover.length; i++) {
                    secondLevelInfo.data.push([secondLevelTurnover[i].levelName, secondLevelTurnover[i].turnover])
                    percents.push(secondLevelTurnover[i].percent)
                }
                // 将新添加的数据放进去，可能存在问题，即多次点击，里面的数组会变多
                // 此处全部置为空，因此可以上面的问题解决，有个优化的点，即每个都点击完后不再进行请求
                drilldownData = []
                drilldownData.push(secondLevelInfo)
            }


            var subData = drilldownData.find(function (data) {
                return data.dataGroupId === event.data.groupId;
            });
            if (!subData) {
                $event = event
                autoClick.click()
                return;
            }

            myChart.setOption({
                xAxis: {
                    data: subData.data.map(function (item) {
                        return item[0];
                    }),
                },
                series: {
                    type: "bar",
                    id: "sales",
                    dataGroupId: subData.dataGroupId,
                    data: subData.data.map(function (item) {
                        return item[1];
                    }),
                    universalTransition: {
                        enabled: true,
                        divideShape: "clone",
                    },
                },
                graphic: [
                    {
                        type: "text",
                        left: 100,
                        // 使 Y 轴出现单位所连带改的设置
                        // left: 120,
                        top: 26,
                        style: {
                            text: "Back",
                            fontSize: 14,
                        },
                        onclick: function () {
                            option && myChart.setOption(option);
                        },
                    },
                ],
            });
        }
    });

    if (option && typeof option === "object") {
        option && myChart.setOption(option);
    }

    window.addEventListener("resize", myChart.resize);
}


// 加载玫瑰图
export async function initRoseDiagramTurnover() {
    let thirdLevelTurnover = await $get('/leftAndRight/thirdLevelTurnover/'+$event.name)

    const option = {
        title: {
            // text: '农产品交易额',
            // subtext: 'Fake Data',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            left: 'center',
            top: 'bottom',
            data: [],
            show:false
        },
        toolbox: {
            show: true,
            feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true }
            },
            // 使工具栏不要超过边框
            right: '20'
        },
        series: [

            {
                name: '农产品交易额',
                type: 'pie',
                radius: [20, 140],
                center: ['50%', '50%'],
                roseType: 'area',
                itemStyle: {
                    borderRadius: 5
                },
                data: []
            }
        ],

        // 使图一出现就显示比例
        // 设置标签显示，直接显示比例信息
        // label: {
        //     show: true,
        //     formatter: '{b} : ({d}%)', // 显示百分比
        // },
    };
    // 开始替换玫瑰图的数据
    option.legend.data = []
    // 是个数组，刚开始没看到
    option.series[0].data = []
    for (let i = 0; i < thirdLevelTurnover.length; i++) {
        option.legend.data.push(thirdLevelTurnover[i].levelName)
        option.series[0].data.push({
            'value': thirdLevelTurnover[i].turnover,
            'name': thirdLevelTurnover[i].levelName
        })
    }
    // 获取显示玫瑰图的容器
    const showRoseDiagram = document.getElementById('showRoseDiagramTurnover')
    const roseChart = echarts.init(showRoseDiagram);
    option && roseChart.setOption(option);
}