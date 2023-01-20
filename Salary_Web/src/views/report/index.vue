<template>
  <div class="column-container">
    <div
      ref="monthWorkOrder"
      class="echarts-box1"
      style="width: 620px; height: 600px"
    ></div>

    <div
      ref="monthWorkOrder1"
      class="echarts-box2"
      style="width: 550px; height: 600px"
    ></div>
  </div>
</template>

<script>
export default {
  name: "Column",
  data() {
    return {};
  },
  mounted() {
    this.initMonthWorkOrder();
    this.initMonthWorkOrder2();
  },
  methods: {
    // 报销申请柱状图
    initMonthWorkOrder() {
      let myChart = this.$echarts.init(this.$refs.monthWorkOrder);

      this.$http
        .get("/reimburse/selecttb")
        .then((res) => {
          let options = {
            title: { text: "报销申请量" },
            tooltip: {
              backgroundColor: "rgba(204, 221, 205, 0.6)",
              trigger: "axis",
              borderColor: "#CCDDFF",
              textStyle: { color: "#0dbc79" },
            },
            color: ["#fbc21c", "#f15d5d"],
            grid: {
              left: "3%",
              right: "4%",
              bottom: "3%",
              containLabel: true,
            },
            xAxis: [
              {
                type: "category",
                data: [
                  "1月",
                  "2月",
                  "3月",
                  "4月",
                  "5月",
                  "6月",
                  "7月",
                  "8月",
                  "9月",
                  "10月",
                  "11月",
                  "12月",
                ],
                axisTick: {
                  alignWithLabel: true,
                },
              },
            ],
            yAxis: [
              {
                type: "value",
                name: " (报销申请量/个）",
                splitLine: {
                  show: true,
                  lineStyle: {
                    type: "dashed",
                    color: "#D3D8DD",
                  },
                },
              },
            ],
            series: [
              // {
              //   name: '成交量',
              //   type: 'bar',
              //   barWidth: 10,
              //   data: res.data.map(x => x.num)
              // }
              {
                name: "报销申请量", //顶部标题
                type: "bar", //类型 柱状
                barWidth: 12, //柱体宽度
                data: res.data.map((x) => x.rnum), //y.leftData即接口方法传递过来的参数也是y轴的数据（y.leftData等同于res.data.yData.leftData）
              },
            ],
          };
          myChart.setOption(options);
          window.addEventListener("resize", function () {
            myChart.resize(); //图表自适应的一个方法
          });
        })
        .catch((err) => {
          console.log(err);
          alert("请求失败！请检查后端接口！！！！");
        });
    },
    // 评论柱状图
    initMonthWorkOrder2() {
      let myChart = this.$echarts.init(this.$refs.monthWorkOrder1);

      this.$http
        .get("/salary/selecttb2")
        .then((res) => {
          let options = {
            title: { text: "工资单量" },
            tooltip: {
              backgroundColor: "rgba(204, 221, 205, 0.6)",
              trigger: "axis",
              borderColor: "#CCDDFF",
              textStyle: { color: "#0dbc79" },
              axisPointer: {
                type: "cross",
              },
            },
            color: ["#fbc21c", "#f15d5d"],
            grid: {
              left: "3%",
              right: "4%",
              bottom: "3%",
              containLabel: true,
            },
            xAxis: [
              {
                type: "category",
                data: [
                  "1月",
                  "2月",
                  "3月",
                  "4月",
                  "5月",
                  "6月",
                  "7月",
                  "8月",
                  "9月",
                  "10月",
                  "11月",
                  "12月",
                ],
                axisTick: {
                  alignWithLabel: true,
                },
                axisPointer: {
                  type: "shadow", //类型
                },
              },
            ],
            yAxis: [
              {
                type: "value",
                name: " (工资单量/个)",
                splitLine: {
                  show: true,
                  lineStyle: {
                    type: "dashed",
                    color: "#D3D8DD",
                  },
                },
              },
            ],
            series: [
              {
                name: "工资单量", //顶部标题
                type: "line", //类型 折线
                data: res.data.map((x) => x.snum), //y.leftData即接口方法传递过来的参数也是y轴的数据（y.rightData等同于res.data.yData.rightData）
              },
            ],
          };
          myChart.setOption(options);
          window.addEventListener("resize", function () {
            myChart.resize(); //图表自适应的一个方法
          });
        })
        .catch((err) => {
          console.log(err);
          alert("请求失败！请检查后端接口！！！！");
        });
    },
  },
};
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
  padding-top: 2px;
}

.anniu {
  margin-left: 20%;
  height: 35px;
}
</style>
