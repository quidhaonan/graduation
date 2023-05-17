import * as echarts from 'echarts'
import 'echarts-gl'
import { $get } from '../../utils/request'


// 将配置定义在外面，便于将两个函数分开
let config
// 定义对象，解决移入显示的问题
const province=['','北京市','天津市','河北省','山西省','内蒙古自治区','辽宁省','吉林省',
            '黑龙江省','上海市','江苏省','浙江省','安徽省','福建省','江西省','山东省',
            '河南省','河北省','湖南省','广东省','广西壮族自治区','海南省','重庆市','四川省',
            '贵州省','云南省','西藏自治区','陕西省','甘肃省','青海省','宁夏回族自治区',
            '新疆维吾尔自治区','香港特别行政区','澳门特别行政区','台湾省']

export async function initShopScatterPlot(shopScatterPlotDom) {
    var app = {};
    // var myChart = echarts.init(shopScatterPlotDom, 'dark');
    var myChart = echarts.init(shopScatterPlotDom);
    var option;

    var indices = {
        name: 0,
        group: 1,
        id: 16
    };
    var schema = [
        { name: '商铺ID', index: 0 },
        { name: '商铺名称', index: 1 },
        { name: '商铺地区', index: 2 },
        { name: '是否是VIP', index: 3 },
        { name: '商铺热度', index: 4 },
        { name: '商铺总营业额', index: 5 },
        { name: '商铺粉丝数', index: 6 },
        { name: '平均发货速度', index: 7 },
        { name: '售后率', index: 8 },
        { name: '复购率', index: 9 },
        { name: '商铺评分', index: 10 },
    ];
    var data;
    var fieldIndices = schema.reduce(function (obj, item) {
        obj[item.name] = item.index;
        return obj;
    }, {});
    var groupCategories = [];
    var groupColors = [];
    var data;
    var fieldNames = schema.map(function (item) {
        return item.name;
    });
    fieldNames = fieldNames.slice(2);
    function getMaxOnExtent(data) {
        var colorMax = -Infinity;
        var symbolSizeMax = -Infinity;
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            var colorVal = item[fieldIndices[config.color]];
            var symbolSizeVal = item[fieldIndices[config.symbolSize]];
            colorMax = Math.max(colorVal, colorMax);
            symbolSizeMax = Math.max(symbolSizeVal, symbolSizeMax);
        }
        return {
            color: colorMax,
            symbolSize: symbolSizeMax
        };
    }
    config = (app.config = {
        xAxis3D: '商铺总营业额',
        yAxis3D: '商铺地区',
        zAxis3D: '是否是VIP',
        color: '商铺地区',
        symbolSize: '商铺评分',
        onChange: function () {
            var max = getMaxOnExtent(data);

            if (data) {
                myChart.setOption({
                    visualMap: [
                        {
                            max: max.color / 2
                        },
                        {
                            max: max.symbolSize / 2
                        }
                    ],
                    xAxis3D: {
                        name: config.xAxis3D,
                        
                    },
                    yAxis3D: {
                        name: config.yAxis3D
                    },
                    zAxis3D: {
                        name: config.zAxis3D
                    },
                    series: {
                        dimensions: [
                            config.xAxis3D,
                            config.yAxis3D,
                            config.yAxis3D,
                            config.color,
                            config.symbolSiz
                        ],
                        data: data.map(function (item, idx) {
                            return [
                                item[fieldIndices[config.xAxis3D]],
                                item[fieldIndices[config.yAxis3D]],
                                item[fieldIndices[config.zAxis3D]],
                                item[fieldIndices[config.color]],
                                item[fieldIndices[config.symbolSize]],
                                idx
                            ];
                        })
                    }
                });
            }
        }
    });
    app.configParameters = {};
    ['xAxis3D', 'yAxis3D', 'zAxis3D', 'color', 'symbolSize'].forEach(function (
        fieldName
    ) {
        app.configParameters[fieldName] = {
            options: fieldNames
        };
    });

    $get('/bigPicture/shopScatterPlot').then((_data)=>{
        data = _data;
        var max = getMaxOnExtent(data);
        myChart.setOption({
            tooltip: {},
            visualMap: [
                {
                    top: 40,
                    calculable: true,
                    dimension: 3,
                    max: max.color / 2,
                    inRange: {
                        color: [
                            '#1710c0',
                            '#0b9df0',
                            '#00fea8',
                            '#00ff0d',
                            '#f5f811',
                            '#f09a09',
                            '#fe0300'
                        ]
                    },
                    textStyle: {
                        // color: '#fff'
                        color:'black'
                    },
                },
                {
                    bottom: 10,
                    calculable: true,
                    dimension: 4,
                    max: max.symbolSize / 2,
                    inRange: {
                        symbolSize: [10, 40]
                    },
                    textStyle: {
                        // color: '#fff'
                        color:'black'
                    }
                }
            ],
            xAxis3D: {
                name: config.xAxis3D,
                type: 'value'
            },
            yAxis3D: {
                name: config.yAxis3D,
                type: 'value'
            },
            zAxis3D: {
                name: config.zAxis3D,
                type: 'value'
            },
            grid3D: {
                axisLine: {
                    lineStyle: {
                        // color: '#fff'
                        color:'black'
                    }
                },
                axisPointer: {
                    lineStyle: {
                        color: '#ffbd67'
                    }
                },
                viewControl: {
                    // autoRotate: true,
                    // projection: 'orthographic'
                },
            },
            series: [
                {
                    type: 'scatter3D',
                    dimensions: [
                        config.xAxis3D,
                        config.yAxis3D,
                        config.yAxis3D,
                        config.color,
                        config.symbolSiz
                    ],
                    data: data.map(function (item, idx) {
                        return [
                            item[fieldIndices[config.xAxis3D]],
                            item[fieldIndices[config.yAxis3D]],
                            item[fieldIndices[config.zAxis3D]],
                            item[fieldIndices[config.color]],
                            item[fieldIndices[config.symbolSize]],
                            idx
                        ];
                    }),
                    symbolSize: 12,
                    // symbol: 'triangle',
                    itemStyle: {
                        borderWidth: 1,
                        borderColor: 'rgba(255,255,255,0.8)'
                    },
                    emphasis: {
                        itemStyle: {
                            color: '#fff'
                        }
                    },
                    tooltip: {
                        formatter: function (params) {
                            const selectXDom=document.getElementById('selectX')
                            const selectYDom=document.getElementById('selectY')
                            const selectZDom=document.getElementById('selectZ')
                            const selectCDom=document.getElementById('selectC')
                            const selectSDom=document.getElementById('selectS')
                            let str=`<table>
                                        <tr>
                                            <th>${data[params.dataIndex][1]}</th>
                                        </tr>`
                            let index
                            const array=[selectXDom.value,selectYDom.value,selectZDom.value,selectCDom.value,selectSDom.value]
                            // 对获得的值进行去重
                            const set=new Set(array)
                            set.forEach((item)=>{
                                index=array.indexOf(item)
                                if(item=='商铺地区'){
                                    str=str+`
                                            <tr>
                                                <td>${array[index]}</td>
                                                <td>：${province[params.data[index]]}</td>
                                            </tr>
                                            `
                                }else if(item=='是否是VIP'){
                                    let isVip=params.data[index]==1?'是':'否'
                                    str=str+`
                                            <tr>
                                             <td>${array[index]}</td>
                                             <td>：${isVip}</td>
                                            </tr>
                                            `
                                }else if(item=='商铺总营业额'){
                                    str=str+`
                                            <tr>
                                                <td>${array[index]}</td>
                                                <td>：${params.data[index]}元</td>
                                            </tr>
                                            `
                                }else if(item=='平均发货速度'){
                                    str=str+`
                                            <tr>
                                                <td>${array[index]}</td>
                                                <td>：${params.data[index]}h</td>
                                            </tr>
                                            `
                                }else if(item=='售后率' || item=='复购率'){
                                    str=str+`
                                            <tr>
                                                <td>${array[index]}</td>
                                                <td>：${params.data[index]}%</td>
                                            </tr>
                                            `
                                }else if(item=='商铺评分' || item=='商铺热度' || item=='商铺粉丝数'){
                                    // 没做什么添加
                                    str=str+`
                                            <tr>
                                                <td>${array[index]}</td>
                                                <td>：${params.data[index]}</td>
                                            </tr>
                                            `
                                }
                            })
                            str=str+'</table>'
                            return str
                        }
                      }
                }
            ],
        });
    })

    option && myChart.setOption(option);
}


// 自己定义的用于改变视图的方法，给每个下拉框绑定同一个事件
export async function initSelect(selectXDom,selectYDom,selectZDom,selectCDom,selectSDom){
    selectXDom.addEventListener('change',()=>{
        // 获取选择框改变后的值
        const selectedIndex = selectXDom.selectedIndex;
        const selectedOption = selectXDom.options[selectedIndex];
        const selectedValue = selectedOption.value

        config.xAxis3D=selectedValue
        config.onChange()
    })
    selectYDom.addEventListener('change',()=>{
        // 获取选择框改变后的值
        const selectedIndex = selectYDom.selectedIndex;
        const selectedOption = selectYDom.options[selectedIndex];
        const selectedValue = selectedOption.value;

        config.yAxis3D=selectedValue
        config.onChange()
    })
    selectZDom.addEventListener('change',()=>{
        // 获取选择框改变后的值
        const selectedIndex = selectZDom.selectedIndex;
        const selectedOption = selectZDom.options[selectedIndex];
        const selectedValue = selectedOption.value;

        config.zAxis3D=selectedValue
        config.onChange()
    })
    selectCDom.addEventListener('change',()=>{
        // 获取选择框改变后的值
        const selectedIndex = selectCDom.selectedIndex;
        const selectedOption = selectCDom.options[selectedIndex];
        const selectedValue = selectedOption.value;

        config.color=selectedValue
        config.onChange()
    })
    selectSDom.addEventListener('change',()=>{
        // 获取选择框改变后的值
        const selectedIndex = selectSDom.selectedIndex;
        const selectedOption = selectSDom.options[selectedIndex];
        const selectedValue = selectedOption.value;

        config.symbolSize=selectedValue
        config.onChange()
    })
}