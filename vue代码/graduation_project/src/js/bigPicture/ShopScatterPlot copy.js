import * as echarts from 'echarts'
import 'echarts-gl'
import { $post } from '../../utils/request'

// 将配置定义在外面，便于将两个函数分开
let config

export async function initShopScatterPlot(shopScatterPlotDom) {
    var app = {};
    var myChart = echarts.init(shopScatterPlotDom, 'dark');
    var option;

    var indices = {
        name: 0,
        group: 1,
        id: 16
    };
    var schema = [
        { name: '店铺ID', index: 0 },
        { name: '店铺名称', index: 1 },
        { name: '店铺地区', index: 2 },
        { name: '是否是VIP', index: 3 },
        { name: '店铺热度', index: 4 },
        { name: '店铺等级', index: 5 },
        { name: '店铺总营业额', index: 6 },
        { name: '店铺粉丝数', index: 7 },
        { name: '平均发货速度', index: 8 },
        { name: '售后率', index: 9 },
        { name: '复购率', index: 10 },
        { name: '平均退款速度', index: 11 },
        { name: '店铺评分', index: 12 },
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
        xAxis3D: '店铺总营业额',
        yAxis3D: '店铺地区',
        zAxis3D: '是否是VIP',
        color: '店铺地区',
        symbolSize: '店铺评分',
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
                        name: config.xAxis3D
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

    $post('/getShopScatterPlot').then((_data)=>{
        data = _data;
        var max = getMaxOnExtent(data);
        myChart.setOption({
            tooltip: {},
            visualMap: [
                {
                    top: 10,
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
                        color: '#fff'
                    }
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
                        color: '#fff'
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
                        color: '#fff'
                    }
                },
                axisPointer: {
                    lineStyle: {
                        color: '#ffbd67'
                    }
                },
                viewControl: {
                    autoRotate: true,
                    // projection: 'orthographic'
                }
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
                    }
                }
            ]
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

        if(selectedValue=='店铺地区'){
            console.log(config.onChange.)
        }
        console.log(selectedValue)

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
        console.log('执行了')
        config.onChange()
    })
}
