<template>
  <div class="Vmanage">

    <el-table :data="list" border="" style="width: 100%" :row-class-name="tableRowClassName">
      <el-table-column prop="vehicleid" label="车辆编号" width="180">
      </el-table-column>

      <el-table-column prop="vehiclename" label="车辆型号" width="250">
      </el-table-column>
      <el-table-column prop="vehicleprice" label="车辆价格（万元）" width="180">
      </el-table-column>
      <el-table-column prop="vehiclecon" label="车况" width="250">
      </el-table-column>


      <el-table-column prop="pic" label="图片" width="200">
        <template slot-scope="scope">
          <el-image :src="scope.row.pic ? 'http://localhost:9999/users' + scope.row.pic : mypic">
          </el-image>
        </template>
      </el-table-column>


      <el-table-column fixed="right" label="" width="200">
        <template slot-scope="scope">
          <div class="car" :style="scope.row.shoppingstate === 1 ? 'background-color: green;' : ''"
            @click="updateCarStatus(scope.row)">
            <div class="el-icon-shopping-cart-2 iconCar" :style="scope.row.shoppingstate === 1 ? 'color:white' : ''">
            </div>
          </div>

          <el-button type="warning"  round @click="updateShow(scope.row)" size="mini">购买</el-button>
        </template>



      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
      :page-sizes="[5, 10, 15, 20]" :page-size="itemsPerPage" layout="total, sizes, prev, pager, next, jumper"
      :total="total" class="bottom" background>
    </el-pagination>


    <el-dialog title="" :visible.sync="dialogForm">
      <el-form :model="updateform" :rules="Rules" ref="updateform">
        <el-form-item label="车辆型号" :label-width="formLabelWidth">
          <el-input v-model="updateform.vehiclename" autocomplete="off"></el-input>

          
        </el-form-item>

        <el-form-item label="价格（万元）" :label-width="formLabelWidth" class="jiage">
          <el-input :value="updateform.vehicleprice" autocomplete="off"></el-input>
          <!-- v-model="updateform.vehiclename" autocomplete="off" -->
        </el-form-item>

        <el-form-item label="车况" :label-width="formLabelWidth">
          <el-input v-model="updateform.vehiclecon" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="ordernote" class="addshuru" label="订单备注" :label-width="formLabelWidth">
          <el-input v-model="updateform.ordernote" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogForm = false">取 消</el-button>
        <el-button type="primary" @click="buyorder('buyform')">确 定</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {

      dialogForm: false,
      mypic: require('@/assets/k.jpg'),
      handleEdit: false,
      page: 1,
      itemsPerPage: 5,
      total: 0,
      list: [],
      param: {
        pageNumber: 1,
        pageSize: 5,
      },
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        vehiclename: "",
      },
      buyform: {
        tradetime: '',
        vehicleprice: "",
        ordernote: '',
      },
      updateform: {
        vehiclename: "",
        vehicleprice: "",
        vehiclecon: "",
        ordernote: ""
      },
      formLabelWidth: "120px",
    };
  },
  methods: {
    updateCarStatus(row) {
      let status = row.shoppingstate === 1 ? 0 : 1
      this.$http.get('/vehicle/updateCarShoppingStatus',
        { params: { vehicleid: row.vehicleid, shoppingstate: status } }).then(res => {
          if (res.data.flag == 'success') {
            this.$message.success(res.data.msg)
            this.fetchData()
          }
        })
    },
    fetchData() {
      /* 当异步发送成功,则执行then,这里的response就是形参
            表示服务器端返回的数据 */
      this.$http("/vehicle/querySome1", { params: this.param }).then(
        (response) => {
          console.log(response);
          /* 获取分页数据 */
          this.list = response.data.rows;
          /* 获取总记录数 */
          this.total = response.data.total;
        }
      );
    },
    updateShow(row) {
      Object.assign(this.updateform, row)

      this.dialogForm = true

    },
    buyorder() {
      this.$refs['updateform'].validate((valid) => {
        if (valid) {
          this.$http.post("/order/buyOrders", this.updateform)
          this.$message.success("购买成功")
          this.fetchData()
          this.dialogForm = false;
          this.$refs[addForm].resetFields()
        } else {
          this.$message.error("表单参数有漏填项，请检查后重新输入！")
          return false;
        }
      });


    },
    handleSizeChange(val) {
      /* 更新每页记录数 */
      this.param.pageSize = val;
      /* 回显 重新查询因为每页记录数更改了 */
      this.fetchData();
    },
    /* 当前页如果更改,执行此函数,val形参就是更改后的当前页的值 */
    handleCurrentChange(val) {
      /* 更新当前页 */
      this.param.pageNumber = val;
      /* 回显 重新查询因为当前页更改了 */
      this.fetchData();
    },
  },
  /* 此函数在Vue实例或者组件最早获得数据时执行, */
  created() {
    /* 调用函数 */
    this.fetchData();
  },
  /* 过滤器 */
  filters: {
    myFilter(val) {
      const roleObj = roleOptions.find((option) => option.id === val);
      return roleObj ? roleObj.type : "";
    },
  },
};
</script>

<style scoped>
.button {
  background-color: white;
}

.table {
  width: 100%;
  height: 100%;
  background-color: #ddd;
  /* 开启弹性盒子 */
  display: flex;
  /* 盒子内部的元素从上往下排列 */
  flex-direction: column;
}

/* 设置表格样式 */
.Vmanage .middle {
  /* 开启弹性盒子后,此参数设置表示,如果外侧容器存在空间,则放大,
        如果外侧容器空间不够,则缩小 */
  flex: 1;
}

/* 设置分页样式 */
.Vmanage .bottom {
  text-align: center;
  height: 5vh;
}


.button {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.car {
  border: 1px solid;
  width: 50px;
  height: 30px;
  border-radius: 20px
}

.iconCar {

  font-size: 25px;
  color: #7a7676;
  margin-left: -3px;
}

/deep/.el-table td.el-table__cell div {
  display: flex;
  justify-content: space-around;
}
.jiage{
   border-top: none !important;
   border-left: none !important;
   border-right: none !important;
   border-radius: 0;
}

/* */
</style>