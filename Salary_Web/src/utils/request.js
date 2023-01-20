/* 
    要向后端发送异步请求,则必须下载一个异步插件,Vue本身没有提供发送异步的功能
    这个插件叫做 axios
        npm i axios -S  
    引入 axios 依赖
*/
import axios from 'axios'

/* 使用axios实例创建一个我们自己的用来发送异步请求的对象 */
const request = axios.create({
    /* 此处表示使用我们自己封装的request发送异步时的基本地址
    那么这里的这个地址就是 配置文件中的 http://db.etoak.com:9527 */
    baseURL: process.env.VUE_APP_BASE_API,
    /* 表示发送异步之后等待响应的时间,超过这个时间立刻报错 */
    timeout:5000,
})

/* 导出我们自己的request对象 */
export default request