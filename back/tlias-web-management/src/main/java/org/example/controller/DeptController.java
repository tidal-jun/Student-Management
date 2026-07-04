package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // = @Controller(IOC容器管理) + @ResponseBody(对象或列表转成JSON)
//因为类中每一个方法路径都有一个/depts，所以可以整体抽出出来，放在类的上方注释，简化代码
//@RequestMapping("/depts") , 但URL获取参数的地址仍然需要占位符
//所以：一个完整的请求路径，应该是类上的 @RequestMapping 的 value 属性 + 方法上的 @RequestMapping 的 value 属性
public class DeptController {

    //可以通过注解@Slf4j，自助建立对象，不用手动建立
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门
     */
    @RequestMapping("depts")
    public Result list(){
//         System.out.println("查询全部部门数据~");
         log.info("查询全部部门数据~");
        List<Dept> deptsList =  deptService.findAll();
        return Result.success(deptsList);
    }

    /**
     * 删除部门 - 方式一：根据HttpServletRequset 获取请求参数
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");      //繁琐
//        int id = Integer.parseInt(idStr);                  //手动类型转换
//        System.out.println("根据ID删除部门：" + id);
//        return Result.success();
//    }

    /**
     * 删除部门 - 方式二：@RequestParam
     * 注意事项：一旦声明了@RequestParam，改参数在请求时必须传递，如果不传递会报错。（默认 required 为 true）
     */
    @Log
    @DeleteMapping("/depts")
    //public Result delete(@RequestParam(value = "id", required = false) Integer depetId){
        //当前端传递的请求参数名与服务端方法形参一致时, 则可以省略@RequestParam
    public Result delete(Integer id){
//        System.out.println("根据ID删除部门：" + id);
        log.info("根据ID删除部门：{}", id);    //用占位符，比拼接简便
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
//    JSON格式的参数，通常会使用一个实体对象进行接收。
//    规则： JSON数据的键名与方法形参对象的属性名相同，并需要使用@RequestBody注标识
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
//        System.out.println("新增部门：" + dept);
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 修改部门
     * 1.查询回显
     * 2.修改数据
     */

    /**
     * 1. 根据ID查询部门
     */
    @GetMapping("/depts/{id}")
    //GetMpping中的{id}占位，会被下方👇@PathVariable("id")接收并传给deptId
//    public Result getInfo(@PathVariable("id") Integer deptId){
    public Result getInfo(@PathVariable Integer id){
//        System.out.println("根据ID查询部门数据：" + id);
        log.info("根据ID查询部门数据：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 2.修改部门
     */
    @Log
    @PutMapping("/depts")
    //此处要接收一个id属性和一个name属性，所以直接使用dept封装了，因为这个实体类里面正好有id和name
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门：" + dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }


}
