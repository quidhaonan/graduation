import * as echarts from 'echarts'
import { $post } from "../../utils/request"

// 加载询价、成交、评价的数据
export async function initInquiryTradedAssess(leftDom,middleDom,rightDom) {
    const left = echarts.init(leftDom)
    const middle = echarts.init(middleDom)
    const right = echarts.init(rightDom)

    const leftOption = {
        tooltip: {
            trigger: "item",
        },
        legend: {
            top: "5%",
            left: "center",
        },
        series: [
            {
                name: "询价人数",
                type: "pie",
                radius: ["40%", "70%"],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: "#fff",
                    borderWidth: 2,
                },
                label: {
                    show: false,
                    position: "center",
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: "bold",
                    },
                },
                labelLine: {
                    show: false,
                },
                data: [
                    { value: 1048, name: "Search Engine" },
                    { value: 735, name: "Direct" },
                    { value: 580, name: "Email" },
                    { value: 484, name: "Union Ads" },
                    { value: 300, name: "Video Ads" },
                ],
            },
        ],
    }
    const middleOption = {
        tooltip: {
            trigger: "item",
        },
        legend: {
            top: "5%",
            left: "center",
        },
        series: [
            {
                name: "Access From",
                type: "pie",
                radius: ["40%", "70%"],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: "#fff",
                    borderWidth: 2,
                },
                label: {
                    show: false,
                    position: "center",
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: "bold",
                    },
                },
                labelLine: {
                    show: false,
                },
                data: [
                    { value: 1048, name: "Search Engine" },
                    { value: 735, name: "Direct" },
                    { value: 580, name: "Email" },
                    { value: 484, name: "Union Ads" },
                    { value: 300, name: "Video Ads" },
                ],
            },
        ],
    }
    const rightOption = {
        tooltip: {
            trigger: "item",
        },
        legend: {
            top: "5%",
            left: "center",
        },
        series: [
            {
                name: "Access From",
                type: "pie",
                radius: ["40%", "70%"],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: "#fff",
                    borderWidth: 2,
                },
                label: {
                    show: false,
                    position: "center",
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: "bold",
                    },
                },
                labelLine: {
                    show: false,
                },
                data: [
                    { value: 1048, name: "Search Engine" },
                    { value: 735, name: "Direct" },
                    { value: 580, name: "Email" },
                    { value: 484, name: "Union Ads" },
                    { value: 300, name: "Video Ads" },
                ],
            },
        ],
    }

    // 加载数据
    await initData(leftOption,middleOption,rightOption)

    leftOption && left.setOption(leftOption)
    middleOption && middle.setOption(middleOption)
    rightOption && right.setOption(rightOption)
}

async function initData(leftOption,middleOption,rightOption){
    // 此时传过来的数据为三个对象，分别对应三个表，每个对象里面的 6 个字段分别对应每个表的分类
    let result=await $post('/getIntervalCounts')
    // 每个表的分类范围不一样，这里是为了简化，后面要定制
    const names=[
            '询价','成交','评价','第四分类',
            '第五分类','第六分类'
    ]

    // 为询价人数替换数据
    leftOption.series[0].data=[]
    leftOption.series[0].data.push({value:result[0].intervalFirst,name:names[0]})
    leftOption.series[0].data.push({value:result[0].intervalSecond,name:names[1]})
    leftOption.series[0].data.push({value:result[0].intervalThird,name:names[2]})
    // leftOption.series[0].data.push({value:result[0].intervalFourth,name:names[3]})
    // leftOption.series[0].data.push({value:result[0].intervalFifth,name:names[4]})
    // leftOption.series[0].data.push({value:result[0].intervalSixth,name:names[5]})
    
    // 为成交人数替换数据
    middleOption.series[0].data=[]
    middleOption.series[0].data.push({value:result[1].intervalFirst,name:names[0]})
    middleOption.series[0].data.push({value:result[1].intervalSecond,name:names[1]})
    middleOption.series[0].data.push({value:result[1].intervalThird,name:names[2]})
    // middleOption.series[0].data.push({value:result[1].intervalFourth,name:names[3]})
    // middleOption.series[0].data.push({value:result[1].intervalFifth,name:names[4]})
    // middleOption.series[0].data.push({value:result[1].intervalSixth,name:names[5]})

    // 为评价人数替换数据
    rightOption.series[0].data=[]
    rightOption.series[0].data.push({value:result[2].intervalFirst,name:names[0]})
    rightOption.series[0].data.push({value:result[2].intervalSecond,name:names[1]})
    rightOption.series[0].data.push({value:result[2].intervalThird,name:names[2]})
    // rightOption.series[0].data.push({value:result[2].intervalFourth,name:names[3]})
    // rightOption.series[0].data.push({value:result[2].intervalFifth,name:names[4]})
    // rightOption.series[0].data.push({value:result[2].intervalSixth,name:names[5]})
}