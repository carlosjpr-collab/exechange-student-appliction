var app = new Vue({
   el: '#HomeStudent_myApplications',
   data: {
		 applications:"par default",
		 items:[]
			 },
   methods:{
		 getStarting: function() {
	  	self=this;
      axios.get('/service/application',{port:8080})
  	.then(function (response) {
		for(var key in response.data) {
		  self.items.push(
				{
					universityName:  response.data[key].university.name,
					statusInsa: 'waiting',
					statusUniversity:"waiting"
				})
		}
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