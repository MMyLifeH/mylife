<template>
  <div class="column-container">

    <div ref="monthWorkOrder" class="echarts-box1" style="width: 550px; height: 500px;">
    </div>

    <div ref="monthWorkOrder1" class="echarts-box2" style="width: 550px; height: 500px;"></div>

  </div>


</template>

<script>
export default {
  name: 'Column',
  data() {
    return {}
  },
  mounted() {
    this.initMonthWorkOrder()
    this.initMonthWorkOrder2()
  },
  methods: {
    // 订单交易柱状图
    initMonthWorkOrder() {
      let myChart = this.$echarts.init(this.$refs.monthWorkOrder)

      this.$http.get('/order/selecttb').then((res) => {
        let options = {
          title: { text: '订单交易量' },
          tooltip: {
            backgroundColor: 'rgba(204, 221, 205, 0.6)',
            trigger: 'axis',
            borderColor: '#CCDDFF',
            textStyle: { color: '#0dbc79' }
          },
          color: ['#fbc21c', '#f15d5d'],
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
              axisTick: {
                alignWithLabel: true
              }
            }
          ],
          yAxis: [
            {
              type: 'value',
              splitLine: {
                show: true,
                lineStyle: {
                  type: 'dashed',
                  color: '#D3D8DD'
                }
              }
            }
          ],
          series: [
            {
              name: '成交量',
              type: 'bar',
              barWidth: 10,
              data: res.data.map(x => x.num)
            }
          ]
        }
        myChart.setOption(options)
      }).catch((err) => {
        console.log(err)
        alert('请求失败！请检查后端接口！！！！')
      })


    },
    // 评论柱状图
    initMonthWorkOrder2() {
      let myChart = this.$echarts.init(this.$refs.monthWorkOrder1)

      this.$http.get('/order/selecttb2').then((res) => {
        let options = {
          title: { text: '用户车评量' },
          tooltip: {
            backgroundColor: 'rgba(204, 221, 205, 0.6)',
            trigger: 'axis',
            borderColor: '#CCDDFF',
            textStyle: { color: '#0dbc79' }
          },
          color: ['#fbc21c', '#f15d5d'],
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
              axisTick: {
                alignWithLabel: true
              }
            }
          ],
          yAxis: [
            {
              type: 'value',
              splitLine: {
                show: true,
                lineStyle: {
                  type: 'dashed',
                  color: '#D3D8DD'
                }
              }
            }
          ],
          series: [
            {
              name: '评论数',
              type: 'bar',
              barWidth: 10,
              data: res.data.map(x => x.num)
            }
          ]
        }
        myChart.setOption(options)
      }).catch((err) => {
        console.log(err)
        alert('请求失败！请检查后端接口！！！！')
      })


    },
    month() {
      this.$axios.get(this.$url + '/selecttb').then((res) => {
        // 读取接口请求成功回传回来的数据
        var orders = res.data.orders
        // 定义数组，存放一会覆盖echarts图形的数据
        var data = []
        // 循环遍历数组，取出数据，转成和data一样的格式
        for (var i = 0; i < ordersum; i++) {
          var d = { name: '', value: 0 }
          d.name = orders[i].name
          d.value = orders[i].num
          // 往data数组中添加数据
          data.push(d)
        }
        // 排序，b - a降序——a - b升序
        // data.sort((a , b) => b.value - a.value)
        // 覆盖data(){}中全局变量的数据
        // this.data = data
        // 画出图形
        this.Draw()
      }).catch((err) => {
        console.log(err)
        alert('请求失败！请检查后端接口！！！！')
      })
    },
    month2() {
      this.$axios.get(this.$url + '/selecttb2').then((res) => {
        // 读取接口请求成功回传回来的数据
        var orders = res.data.orders
        // 定义数组，存放一会覆盖echarts图形的数据
        var data = []
        // 循环遍历数组，取出数据，转成和data一样的格式
        for (var i = 0; i < ordersum; i++) {
          var d = { name: '', value: 0 }
          d.name = orders[i].name
          d.value = orders[i].num
          // 往data数组中添加数据
          data.push(d)
        }
        // 排序，b - a降序——a - b升序
        // data.sort((a , b) => b.value - a.value)
        // 覆盖data(){}中全局变量的数据
        // this.data = data
        // 画出图形
        this.Draw()
      }).catch((err) => {
        console.log(err)
        alert('请求失败！请检查后端接口！！！！')
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.column-container {
  height: 100%;
}

.echarts-box1 {
  margin-left: 5%;
  float: left;
}

.echarts-box2 {
  margin-right: 5%;
  float: right;
}

.bm {

  padding-top: 2px
}

.anniu {
  margin-left: 20%;
  height: 35px;
}
</style>
