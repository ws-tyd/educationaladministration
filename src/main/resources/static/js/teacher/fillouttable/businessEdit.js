var app = new Vue({
    el: "#app",
    data() {
        return {
            ifShow: false,
            teacher: {},
            businessDTO: {
                dateOfBirth:" ",
                institute: " ",
                section: " ",
                year:new Date().getFullYear(),
                trem:"上",
                completeWorkload: [{
                    courseName: " ",
                    classes: " ",
                    theoryClass: " ",
                    guidingExperiment: " ",
                    guidingDesign: " ",
                    guidingInternship: " ",
                    guideComprehensiveExperiment: " ",
                    totalClass: " "
                }],
                totalWorkload: " ",
                academicAchievements: {
                    textBook: [{
                        textbookName: " ",
                        publishingUnitAndTime: " ",
                        chiefEditor: " ",
                        jointlyEdited: " ",
                        planningTextbook: " ",
                        schoolBasedTextbook: " ",
                        other: " "
                    }],
                    paper:[{
                        paperTitle:" ",
                        publishingSituation:" ",
                        level:" ",
                        ranking:" "
                    }],
                    projectOrTopics:[{
                        hostSubject:" ",
                        cooperationUnit:" ",
                        category:" ",
                        myRole:" "
                    }]
                },

                awards:" ",
                academicGroup:" ",
                continuingEducation:" ",
                otherJobs:" ",
                sick:" ",
                leave:" ",
                beLate:0,
                leaveEarly:0,
                tuneClass:0,
                missingClass:0,
                disciplinaryPenalty:0,
                //下面不要填。。
                teachingOpinion:" ",
                departmentOpinion:" ",
                schoolOpinion:" "
            },
            copyDTO:null

        }
    },
    methods: {
        add1(){
            this.businessDTO.completeWorkload.push({});
        },
        add2(){
            this.businessDTO.academicAchievements.textBook.push({});
        },
        add3(){
            this.businessDTO.academicAchievements.paper.push({});
        },
        add4(){
            this.businessDTO.academicAchievements.projectOrTopics.push({});
        },

        del1(){
            this.businessDTO.completeWorkload.pop();
        },
        del2(){
            this.businessDTO.academicAchievements.textBook.pop();
        },
        del3(){
            this.businessDTO.academicAchievements.paper.pop();
        },
        del4(){
            this.businessDTO.academicAchievements.projectOrTopics.pop();
        },

        save(){
            axios.post("/teacher/business",
                this.businessDTO
            ).then((data)=>{
                if(data.data.code==100){
                alert("保存成功");
            }else{
                alert("保存失败");
            }
        });
        },
        reset(){
            this.businessDTO = JSON.parse(JSON.stringify(this.copyDTO));
        },
        print(){
            this.ifShow = true;
            //打印前，先保存一下
            this.save();
            //换行转换一下 todo 暂时没解决
            // for (let key in this.businessDTO) {
            //     console.info("key:",key);
            //     this.$set(this.businessDTO, key,this.replace(this.businessDTO[key]));
            // }

            //1 秒 后调用打印
            setTimeout(
                function(){
                    window.print();
                },1000);
        },
        replace(str){

        },
        update(){
            this.$set(this.businessDTO,"year",this.copyDTO.year);
            this.$set(this.businessDTO,"trem",this.copyDTO.trem);

            this.getBusiness();
        },
        getBusiness(){
            axios.get("/teacher/businessData",
                {
                    params:{
                        year:this.businessDTO.year,
                        trem:this.businessDTO.trem
                    }
                }
            ).then((data)=>{

                console.log(data);

            if (data.data.extend.teacher != null)
                this.teacher = data.data.extend.teacher;
            let _businessDTO = data.data.extend.businessDTO;
            //无论有没有值都重置一下
            this.businessDTO = JSON.parse(JSON.stringify(this.copyDTO));
            // 遍历属性为空不赋值
            if (_businessDTO != null) {
                for (let key in _businessDTO) {
                    this.$set(this.businessDTO, key, _businessDTO[key]);
                }
            }

        });
        }
    },
    created(){
        let copy1 = JSON.parse(JSON.stringify(this.businessDTO))
        this.copyDTO = JSON.parse(JSON.stringify(this.businessDTO));
        this.getBusiness();
    },
    mounted() {
    }
})