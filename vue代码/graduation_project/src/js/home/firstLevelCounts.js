import * as echarts from "echarts"
import { $post } from "../../utils/request"

// 如果 initFirstLevel 调用 initRoseDiagram 的话，就获取不到显示玫瑰图的容器
// 因此打算在 initFirstLevel 的方法中给该属性赋值键盘事件，以便 initRoseDiagram 使用
let $event

// 加载第一第二级分类的数据
export async function initFirstLevel(secondLevelCounts, autoClick) {
    let firstLevelCounts = await $post('/getFirstLevelCounts')
    // console.log(firstLevelCounts)

    // var dom = document.getElementById("secondLevelCounts");
    let myChart = echarts.init(secondLevelCounts, null, {
        renderer: "canvas",
        useDirtyRect: false,
    });
    let app = {};

    let option;

    option = {
        xAxis: {
            data: ["蔬菜", "农资农机", "粮油米面", '种子种苗', '农副加工', '水产', '水果', '禽畜肉蛋'],
        },
        yAxis: {},
        dataGroupId: "",
        animationDurationUpdate: 500,
        series: {
            type: "bar",
            id: "sales",
            data: [
                {
                    value: firstLevelCounts[0].lastCounts,
                    groupId: firstLevelCounts[0].firstNo,
                },
                {
                    value: firstLevelCounts[1].lastCounts,
                    groupId: firstLevelCounts[1].firstNo,
                },
                {
                    value: firstLevelCounts[2].lastCounts,
                    groupId: firstLevelCounts[2].firstNo,
                },
                {
                    value: firstLevelCounts[3].lastCounts,
                    groupId: firstLevelCounts[3].firstNo,
                },
                {
                    value: firstLevelCounts[4].lastCounts,
                    groupId: firstLevelCounts[4].firstNo,
                },
                {
                    value: firstLevelCounts[5].lastCounts,
                    groupId: firstLevelCounts[5].firstNo,
                },
                {
                    value: firstLevelCounts[6].lastCounts,
                    groupId: firstLevelCounts[6].firstNo,
                },
                {
                    value: firstLevelCounts[7].lastCounts,
                    groupId: firstLevelCounts[7].firstNo,
                },
            ],
            universalTransition: {
                enabled: true,
                divideShape: "clone",
            },
        },
    };
    // 原先有数据的，但是现在是通过动态的增加，因此置为空
    let drilldownData = []

    myChart.on("click", async function (event) {
        if (event.data) {

            // 自己加的
            // 判断是否有 groupId 这个值，来决定是否请求 ajax（最后一级的时候没有该属性）
            if (event.data.groupId) {
                // 第二层的数据向后台请求
                let secondLevelCounts = await $post('/getSecondLevelCounts', { 'firstNo': event.data.groupId })
                const secondLevelInfo = {
                    // 与第一级进行绑定的依据
                    dataGroupId: event.data.groupId,
                    data: []
                }
                // 通过请求过来的数据，动态的对第二级的内容进行添加
                for (let i = 0; i < secondLevelCounts.length; i++) {
                    secondLevelInfo.data.push([secondLevelCounts[i].levelName, secondLevelCounts[i].lastCounts])
                }
                // 将新添加的数据放进去，可能存在问题，即多次点击，里面的数组会变多
                // 此处全部置为空，因此可以上面的问题解决，有个优化的点，即每个都点击完后不再进行请求
                drilldownData = []
                drilldownData.push(secondLevelInfo)
            }


            var subData = drilldownData.find(function (data) {
                return data.dataGroupId === event.data.groupId;
            });
            // console.log(subData)
            if (!subData) {
                $event=event
                autoClick.click()
                console.log("推出去");
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
                        left: 50,
                        top: 20,
                        style: {
                            text: "Back",
                            fontSize: 18,
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
export async function initRoseDiagram(){
    // console.log($event.name)
    let thirdLevelCounts=await $post('/getThirdLevelCounts',{'levelName':$event.name})
    // console.log(thirdLevelCounts)

    const option = {
        title: {
          text: '商品交易数量',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          top: 'bottom',
          data: [
            'rose1',
            'rose2',
            'rose3',
            'rose4',
            'rose5',
            'rose6',
            'rose7',
            'rose8'
          ]
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [
         
          {
            name: 'Area Mode',
            type: 'pie',
            radius: [20, 140],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 5
            },
            data: [
              { value: 30, name: 'rose 1' },
              { value: 28, name: 'rose 2' },
              { value: 26, name: 'rose 3' },
              { value: 24, name: 'rose 4' },
              { value: 22, name: 'rose 5' },
              { value: 20, name: 'rose 6' },
              { value: 18, name: 'rose 7' },
              { value: 16, name: 'rose 8' }
            ]
          }
        ]
      };
    // 开始替换玫瑰图的数据
    option.legend.data=[]
    // 是个数组，刚开始没看到
    option.series[0].data=[]
    for(let i=0;i<thirdLevelCounts.length;i++){
        option.legend.data.push(thirdLevelCounts[i].levelName)
        option.series[0].data.push({
            'value':thirdLevelCounts[i].lastCounts,
            'name':thirdLevelCounts[i].levelName
        })
    }

    // 获取显示玫瑰图的容器
    const showRoseDiagram=document.getElementById('showRoseDiagram')
    const roseChart = echarts.init(showRoseDiagram);
    option && roseChart.setOption(option);
}