<template>
  <div class="vehicle">
    <div class="button">
      <div>
        <el-button type="primary" @click="showAdd()">添加车辆信息
        </el-button>
      </div>
      <div class="queryRight">
        <el-input class="input" v-model="queryForm.vehiclename" placeholder="请输入内容">

        </el-input>
        <el-button type="primary" style="margin-right:40px" @click="fetchData()">搜索</el-button>
      </div>
    </div>


    <el-table :data="list" style="width: 100%" border="2px" :row-class-name="tableRowClassName">
      <el-table-column prop="vehicleid" label="车辆编号" width="180">
      </el-table-column>

      <el-table-column prop="vehiclename" label="车辆型号" width="250">
      </el-table-column>
      <el-table-column prop="vehicleprice" label="车辆价格（万元）" width="150">
      </el-table-column>
      <el-table-column prop="vehiclecon" label="车况" width="280">
      </el-table-column>


      <el-table-column prop="pic" label="图片" width="200">
        <template slot-scope="scope">
          <el-image :src="scope.row.pic ? 'http://localhost:9999/users' + scope.row.pic : mypic">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="175">

        <template slot-scope="scope">
          <el-button type="primary" @click="updateShow(scope.row)">修改</el-button>

          <el-button type="danger" @click="deletevehicle(scope.row.vehicleid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
      :page-sizes="[10, 15, 30, 50]" :page-size="itemsPerPage" layout="total, sizes, prev, pager, next, jumper"
      :total="total" class="bottom" background>
    </el-pagination>
    <!-- 添加  -->
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :model="addform" :rules="Rules" ref="addform">
        <el-form-item label="车辆型号" :label-width="formLabelWidth">
          <el-input v-model="addform.vehiclename" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="车辆价格（万元）" :label-width="formLabelWidth">
          <el-input v-model="addform.vehicleprice" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="车况" :label-width="formLabelWidth">
          <el-input v-model="addform.vehiclecon" autocomplete="off"></el-input>
        </el-form-item>

        <el-upload  class="upload-demo" name="pic" action="http://localhost:9999/users/vehicle/addpic"
          :on-success="success" :limit="1" :on-exceed="exceed" :file-list="addfileList" list-type="picture">
          <el-button size="small" type="primary">上传车辆图片</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addvehicle('addform')">确 定</el-button>
      </div>

    </el-dialog>

    <!-- 修改 -->
    <el-dialog title="编辑" :visible.sync="dialogForm">
      <el-form :model="updateform" :rules="Rules" ref="updateform">
        <el-form-item label="车辆型号" :label-width="formLabelWidth">
          <el-input v-model="updateform.vehiclename" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="车辆价格（万元）" :label-width="formLabelWidth">
          <el-input v-model="updateform.vehicleprice" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="车况" :label-width="formLabelWidth">
          <el-input v-model="updateform.vehiclecon" autocomplete="off"></el-input>
        </el-form-item>
        <el-upload class="upload-demo" name="pic" action="http://localhost:9999/users/vehicle/addpic" :on-success="su"
          :limit="1" :on-exceed="exceed" :file-list="fileList" list-type="picture">
          <el-button size="small" type="primary">修改车辆图片</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogForm = false">取 消</el-button>
        <el-button type="primary" @click="updatevehicle('updateform')">确 定</el-button>
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
      mypic: require('@/assets/k.jpg'),
      dialogForm: false,
      queryForm: {
        vehiclename: "",
      },
      addfileList:[],
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
        vehiclename: "",
      },
      addform: {
        vehiclename: "",
        vehicleprice: "",
        vehiclecon: "",
        pic: "",
      },
      updateform: {
        vehiclename: "",
        vehicleprice: "",
        vehiclecon: "",
        pic: "",
      },
      Rules: {
        vehiclename: [
          { required: true, message: '请输入评论内容', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    showAdd() {
      this.addfileList = []
      
      this.addform = {}
      this.dialogFormVisible = true
    },
    // 图片限制条件，上传
    exceed() {
      this.$message.error('车辆图片只能上传一张')
    },
    success(response, file, fileList) {
      this.addform.pic = response.savepath
    },
    su(response, file, fileList) {
      this.updateform.pic = response.savepath
    },
    // 查询
    fetchData() {
      /* 当异步发送成功,则执行then,这里的response就是形参
            表示服务器端返回的数据 */
      dao.queryVehicles(this.page, this.itemsPerPage, this.queryForm.vehiclename).then((response) => {
        console.log(response);
        /* 获取分页数据 */
        this.list = response.data.rows;
        /* 获取总记录数 */
        this.total = response.data.total;
      });
    },
    // 添加
    addvehicle(addForm) {
      this.$refs[addForm].validate((valid) => {
        if (valid) {
          this.$http.post("/vehicle/add", this.addform)
          this.$message.success("添加成功")
          this.fetchData()
          this.$refs[addForm].resetFields()
          this.dialogFormVisible = false;
        } else {
          this.$message.error("表单参数有漏填项，请检查后重新输入！")
          return false;
        }
      });
    },
    // 添加图片
    addp() {
      this.$http.post("ehicle/addpic", this.addform).then((res) => {
        if (res.data.flag == "") {

        }
      });
    },
    // 删除
    deletevehicle(vehicleid) {
      this.$confirm('确认删除车辆信息吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.get("http://localhost:9999/users/vehicle/delete?vehicleid=" + vehicleid).then((res) => {
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
    // 修改
    updatevehicle(updateForm) {
      this.$refs[updateForm].validate((valid) => {
        if (valid) {
          this.$http.post("/vehicle/update", this.updateform).then(res => {

            this.$message.success("修改成功")
            this.fetchData()
            this.dialogForm = false;
          })
        } else {
          this.$message.error("表单参数有漏填项，请检查后重新输入！")
          return false;
        }
      });
    },
    // 显示原数据
    updateShow(row) {
      Object.assign(this.updateform, row)

      this.fileList = []
      this.dialogForm = true

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
/* 设置表格样式 */
.vehicle .middle {
  /* 开启弹性盒子后,此参数设置表示,如果外侧容器存在空间,则放大,
        如果外侧容器空间不够,则缩小 */
  flex: 1;
}

/* 设置分页样式 */
.vehicle .bottom {
  text-align: center;
  height: 5vh;
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