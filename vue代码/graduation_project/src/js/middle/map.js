import {$post} from '../../utils/request'
import * as echarts from 'echarts/core'
import '../china'

// 加载地图数据
export async function initMap(container) {
    let result =await $post('/getProvinceCounts')
    const dataList = [
      { name: "南海诸岛", value: 0 },
      { name: "北京", value: result[0].counts },
      { name: "天津", value: result[1].counts },
      { name: "河北", value: result[2].counts },
      { name: "山西", value: result[3].counts },
      { name: "内蒙古", value: result[4].counts },
      { name: "辽宁", value: result[5].counts },
      { name: "吉林", value: result[6].counts },
      { name: "黑龙江", value: result[7].counts },
      { name: "上海", value: result[8].counts },
      { name: "江苏", value: result[9].counts },
      { name: "浙江", value: result[10].counts },
      { name: "安徽", value: result[11].counts },
      { name: "福建", value: result[12].counts },
      { name: "江西", value: result[13].counts },
      { name: "山东", value: result[14].counts },
      { name: "河南", value: result[15].counts },
      { name: "湖北", value: result[16].counts },
      { name: "湖南", value: result[17].counts },
      { name: "广东", value: result[18].counts },
      { name: "广西", value: result[19].counts },
      { name: "海南", value: result[20].counts },
      { name: "重庆", value: result[21].counts },
      { name: "四川", value: result[22].counts },
      { name: "贵州", value: result[23].counts },
      { name: "云南", value: result[24].counts },
      { name: "西藏", value: result[25].counts },
      { name: "陕西", value: result[26].counts },
      { name: "甘肃", value: result[27].counts },
      { name: "青海", value: result[28].counts },
      { name: "宁夏", value: result[29].counts },
      { name: "新疆", value: result[30].counts },
      { name: "香港", value: result[31].counts },
      { name: "澳门", value: result[32].counts },
      // { name: "台湾", value: result[33].counts },
      { name: "台湾", value: 0 }
    ]
    const myChart = echarts.init(container);
    const option = {
      tooltip: {
        formatter: function (params, ticket, callback) {
          return (
            params.seriesName + "<br />" + params.name + "：" + params.value
          )
        },
      },
      // visualMap: {
      //   min: 0,
      //   max: 1500,
      //   left: "left",
      //   top: "bottom",
      //   text: ["高", "低"],
      //   inRange: {
      //     color: ["#e0ffff", "#006edd"],
      //   },
      //   show: true,
      // },
      // 设置分级和分级的颜色
      visualMap:{
        x:10,
        y:20,
        splitList:[
          {start:10000,end:50000},
          {start:9000,end:10000},
          {start:7000,end:9000},
          {start:5000,end:7000},
          {start:3000,end:5000},
          {start:0,end:3000},
        ],
        color:['#5475f5','#9feaa5','#85daef','#74e2ca','#e6ac53','#9fb5ea'],
        show:true
      },
      geo: {
        map: "china",
        // 控制地图是否可以被拖拽 和缩放
        // roam: true,
        roam: false,
        // 地图是否放大
        zoom: 1,
        // 控制字体是否可见，字体大小和颜色
        label: {
          normal: {
            show: true,
            fontSize: "10",
            color: "rgba(0,0,0,0.7)",
          },
        },
        itemStyle: {
          normal: {
            // 省的边框颜色
            borderColor: "rgba(0,0,0,0.2)",
          },
          // 选中情况
          emphasis: {
            // 选中变颜色
            areaColor: "red",
            // 选中偏移
            shadowOffsetX: 1,
            shadowOffsetY: 1,
            shadowBlur: 20,
            borderWidth: 0,
            // 控制阴影颜色
            shadowColor: "rgba(0,0,0,0.5)",
          },
        },
      },
      series: [
        {
          name: "农产品数量",
          type: "map",
          geoIndex: 0,
          data: dataList,
        },
      ],
    };
    myChart.on("click", function (params) {
      console.log(params);
    });
    option && myChart.setOption(option);
    window.onresize = function () {
      myChart.resize();
    };
  }
