var app = new Vue({
   el: '#HomeStudent_apply',
   data: {
		 universityName:"par default",
		 search:"",
         list_university: [],
		 acceptV:false,
		 universityId:"par default",
		 universityCountry:"par default",
		 universityCity:"par default",
		 universityUrl:"par default",
		 universityCourses:"par default",
		 successApply:"",
		 showUniversityActive:false
			 },
	 computed: {
		 acceptPolicy(){
			return !this.acceptV;
		 },
    filteredList() {
      return this.list_university.filter(university => {
        return university.name.toLowerCase().includes(this.search.toLowerCase())
      })
    }},
   methods:{
	   apply: function(id){
			console.log(id);
			var vue = this;
			var json = {
							"idUniv" : id,
							"agreement" : 1,
							"status" : "wait for university"
			};
			axios
				.put('/service/application', json)
				.then(function(response){
					obj = JSON.parse(JSON.stringify(response.data));
					console.log(obj.success);
					if (obj.success == "true") {
			            vue.successApply = "Your application has been submitted !";
			        }
					else{
			            vue.successApply = "You already have applied to this university !";
					}
				})
				.catch(function (error) {
			          console.log(error);
		        })
			}
			,

		 openbox: function(index){
			 	var self=this;
			    this.acceptV=false;
			 	this.showUniversityActive=true;
			 	this.universityName=this.list_university[index].name;
				this.universityId=this.list_university[index].id;
				this.universityCity=this.list_university[index].city;
				this.universityUrl=this.list_university[index].url;
				this.universityCountry=this.list_university[index].country;
				axios.get('/service/course?id='+this.list_university[index].id,{port:8080})
	      .then(function (response) {
	    	  self.universityCourses=response.data;
			})},

		 getStarting: function() {
	  	self=this;
      axios.get('/service/university',{port:8080})
  .then(function (response) {
	 self.list_university=response.data;
	 self.list_university.forEach(function(element) { element.active = false; });
   console.log(self.list_university);
  })
  .catch(function (error) {
    // handle error
    console.log(error);
  })
  .then(function () {
    // always executed
  });
       }
 },
 beforeMount(){
    this.getStarting()
 },
 })