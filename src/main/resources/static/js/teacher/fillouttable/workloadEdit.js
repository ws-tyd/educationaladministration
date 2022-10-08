var app = new Vue({
    el: "#app",
    data() {
        return {
            ifShow:false,
            age:"",
            teacher:{},
            workloadDTO:{
                section:" ",
                institute:" ",
                year:new Date().getFullYear(),
                trem:"上",
                // 教学工作量
                teachingWork: [
                    {
                        classes: " ",
                        courseName: " ",
                        classHourOfPlan: " ",
                        jojintClass: " ",
                        extracurricularExperiment: " ",
                        courseDesign: " ",
                        on_campusPractice: " ",
                        off_campusTraining: " ",
                        GraduationPractice: " ",

                    }
                ],
                sum1: " ",
                sum2: " ",
                sum1AddSum2: " ",
                // teach study section study work do amount
                // 教研科研工作量
                tseswdas:[
                    {
                        rsrProjectName:" ",
                        rsrProjectBrief:" ",
                        rsrProjectStatus:" ",
                        rsrConversionWorkload:" ",
                        rsrPProjectLeader:" "
                    }
                ],
                rsrWorkload:" ",
                // 补助工作量 ...字段名懒得改了
                subsidyworkloadDATA:{
                    rsrProjectName:" ",
                    rsrProjectBrief:" ",
                    rsrProjectStatus:" ",
                    rsrConversionWorkload:" ",
                    rsrPProjectLeader:" ",
                },
                subsidyworkload:" ",
                // 总工作量
                totalSum:" ",
                // 教研室审核
                sectionVerify:" ",
                // 院系审核
                instituteVerify:" "
            },
            newWorkloadDTO:{}
        }
    },
    methods: {
        add(){
            this.workloadDTO.teachingWork.push({});
        },
        del(){
            this.workloadDTO.teachingWork.pop();
        },
        add2(){
            this.workloadDTO.tseswdas.push({});
        },
        del2(){
            this.workloadDTO.tseswdas.pop();
        },
        save(){
            axios.post("/teacher/wordload",
                this.workloadDTO
            ).then((data)=>{
                if(data.data.code==100){
                alert("保存成功");
            }else{
                alert("保存失败");
            }
        })
        },
        reset(){
            this.$set(this,'workloadDTO', JSON.parse(JSON.stringify(this.newWorkloadDTO)));
        },
        print(){
            this.ifShow = true;
            //打印前，先保存一下
            this.save();
            //1 秒 后调用打印
            setTimeout(
                function(){
                    window.print();
                },1000);
        },
        updateWorkload(){

            this.workloadDTO.year = this.newWorkloadDTO.year;
            this.workloadDTO.trem = this.newWorkloadDTO.trem;

            this.getWorkload();
        },
        getWorkload(){
            axios.get("/teacher/wordloadData",
                {
                    params:{
                        year:this.workloadDTO.year,
                        trem:this.workloadDTO.trem
                    }
                }
            ).then((data) => {
                this.teacher=data.data.extend.teacher;
            var _Workerload  = data.data.extend.workloadDTO;
            if(_Workerload==null){
                //不用$set赋值 vue 视图 无法更新。。。
                this.$set(this,'workloadDTO', JSON.parse(JSON.stringify(this.newWorkloadDTO)));
            }else{
                this.$set(this,'workloadDTO', JSON.parse(JSON.stringify(this.newWorkloadDTO)));
                for (let key in _Workerload) {
                    this.$set(this.workloadDTO, key, _Workerload[key]);
                }
            }
        });
        }
    },
    mounted(){
        this.newWorkloadDTO = JSON.parse(JSON.stringify(this.workloadDTO))
        //加载初始信息
        this.getWorkload();
    }
})