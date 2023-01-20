<template>
  <div class="ddgl">
    <div class="button">
      <div>
        <el-button type="primary" @click="showAdd()">添加订单
        </el-button>

      </div>
      <div class="queryRight">
        <el-input class="input" v-model="queryForm.ordernote" placeholder="请输入内容">
          <!-- <i slot="prefix" class="el-input__icon el-icon-search"></i> -->
        </el-input>
        <el-button type="primary" style="margin-right:40px" @click="fetchData()">搜索</el-button>
      </div>

    </div>

    <el-table class="middle" :data="list" border stripe highlight-current-row>
      <el-table-column label="订单编号" prop="or_id" align="center" width="100px"></el-table-column>
      <!-- prop:表示对应字段名 -->
      <el-table-column label="交易时间" align="center" width="250px" prop="tradetime"></el-table-column>
      <el-table-column label="订单金额(万元)" align="center" width="250px" prop="ordermoney"></el-table-column>
      <el-table-column label="订单备注" align="center" prop="ordernote"></el-table-column>
      <el-table-column label="操作" align="center" prop="caozuo">
        <template slot-scope="scope">
          <el-button size="primary" @click="updateShow(scope.row)">修改</el-button>

          <el-button type="danger" @click="deleteOrder(scope.row.or_id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
      :page-sizes="[10, 15, 30, 50]" :page-size="itemsPerPage" layout="total, sizes, prev, pager, next, jumper"
      :total="total" class="bottom" background>
    </el-pagination>
    <!-- 添加订单 -->
    <el-dialog title="添加订单" :visible.sync="dialogFormVisible" >
      <el-form :model="addform" :rules="orderRules" ref="addform">
        <el-form-item label="选择日期" :label-width="formLabelWidth" prop="tradetime">


          <el-date-picker v-model="addform.tradetime" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"
            style="padding-left:4%">
          </el-date-picker>
        </el-form-item>

        <el-form-item prop="ordermoney" class="addshuru" label="交易金额" :label-width="formLabelWidth">
          <el-input v-model.number="addform.ordermoney" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="ordernote" class="addshuru" label="订单备注" :label-width="formLabelWidth">
          <el-input v-model="addform.ordernote" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取消</el-button>
        <el-button type="primary" @click="addorder('addform')">添加</el-button>
      </div>
    </el-dialog>
    <!-- 修改订单 -->
    <el-dialog title="修改订单" :visible.sync="dialogFormVisible1" >
      <el-form :model="updateform" :rules="orderRules" ref="updateform">
        <el-form-item label="选择日期" :label-width="formLabelWidth" prop="tradetime">

          <el-date-picker v-model="updateform.tradetime" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"
            style="padding-left:4%">
          </el-date-picker>
        </el-form-item>

        <el-form-item prop="ordermoney" class="addshuru" label="交易金额" :label-width="formLabelWidth">
          <el-input v-model="updateform.ordermoney" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item prop="ordernote" class="addshuru" label="订单备注" :label-width="formLabelWidth">
          <el-input v-model="updateform.ordernote" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1=false">取 消</el-button>
        <el-button type="primary" @click="updateorder('updateform')">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
/* 导入 dao.js中默认成员 */
import dao from "@/api/dao";

export default {
  data() {
    return {
      dialogFormVisible1: false,
      queryForm: {
        ordernote: ''
      },
      formLabelWidth: 200,
      /* 当前页 */
      page: 1,
      /* 每页记录数 */
      itemsPerPage: 10,
      /* 总记录数 */
      total: 0,
      /* 分页数据 */
      list: [],
      dialogFormVisible: false,
      form: {
        name: "",
      },
      addform: {
        tradetime: '',
        ordermoney: '',
        ordernote: '',
      },
      updateform: {
        tradetime: '',
        ordermoney: '',
        ordernote: '',
      },
      orderRules: {
        tradetime: [
          { required: true, message: '选择日期', trigger: 'blur' },
        ],
        ordermoney: [
          { required: true, message: '请输入交易金额', trigger: 'blur' },
          { type: 'number', message: '必须为数字值'}
        ],
        ordernote: [
          { required: true, message: '请输入订单备注', trigger: 'blur' },
        ],
      },
    };

  },

  methods: {
    showAdd() {
      this.addform = {}
      this.dialogFormVisible = true
    },
    updateShow(row) {
      Object.assign(this.updateform, row)

      this.dialogFormVisible1 = true

    },
      canceladd(){
      this.dialogFormVisible=false
      this.$message.info("已取消添加")
    },
    cancelupdate(){
      this.dialogFormVisible1=false
      this.$message.info("已取消修改")
    },
    /* 此函数用来调用方法发送异步请求 */
    fetchData() {
      /* 当异步发送成功,则执行then,这里的response就是形参
            表示服务器端返回的数据 */
      dao.queryOrders(this.page, this.itemsPerPage, this.queryForm.ordernote).then((response) => {
        console.log(response);
        /* 获取分页数据 */
        this.list = response.data.rows;
        /* 获取总记录数 */
        this.total = response.data.total;
      });
    },
    addorder(addForm) {
      this.$refs[addForm].validate((valid) => {
        if (valid) {
          this.$http.post("/order/addOrders", this.addform)
          this.$message.success("添加成功")
          this.fetchData()
          this.dialogFormVisible = false;
          this.$refs[addForm].resetFields()
        } else {
          this.$message.error("表单参数有漏填项，请检查后重新输入！")
          return false;
        }
      });


    },

    //  修改订单
    updateorder(updateForm) {
      this.$refs[updateForm].validate((valid) => {
        if (valid) {
          this.$http.post("/order/updateorder", this.updateform).then(res => {

            this.$message.success("修改成功")
            this.fetchData()
            this.dialogFormVisible1 = false;
          })

          // this.$refs[updateForm].resetFields()                  
        } else {
          this.$message.error("表单参数有漏填项，请检查后重新输入！")
          return false;
        }
      })
    },

    // 删除订单
    deleteOrder(id) {

      this.$confirm('确认删除此订单吗', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.get("http://localhost:9999/users/order/deleteorder?id=" + id).then((res) => {
          this.$message(res.data)
          this.fetchData()

        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    /* 每页记录数如果更改,则执行此函数 val形参就表示
        更改后的每页记录数 */
    handleSizeChange(val) {
      /* 更新每页记录数 */
      this.itemsPerPage = val;
      /* 回显 重新查询因为每页记录数更改了 */
      this.fetchData();
    },
    /* 当前页如果更改,执行此函数,val形参就是更改后的当前页的值 */
    handleCurrentChange(val) {
      /* 更新当前页 */
      this.page = val;
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
.table .middle {
  /* 开启弹性盒子后,此参数设置表示,如果外侧容器存在空间,则放大,
        如果外侧容器空间不够,则缩小 */
  flex: 1;
}

/* 设置分页样式 */
.table .bottom {
  text-align: center;
  height: 5vh;
}

.el-pagination {
  padding-left: 20%;
}


.button {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.queryRight {
  display: flex;
  flex-direction: row;
}
</style>
