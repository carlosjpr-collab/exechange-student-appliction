var app = new Vue({
   el: '#Homeuniversity_myApplications',
   data: {
		 applications:"par default",
		 items:[],
		 fields: [
		        { key: 'FirstName', sortable: true },
		        { key: 'LastName', sortable: true },
		        { key: 'InsaRanking', sortable: true },
		        { key: 'status', sortable: true },
		        { key: 'url', sortable: false }
		      ],
		 showloader: true
			 },
   methods:{
	   accept:function(item){
		   console.log(item)
	   },
	   refuse:function(item){
		   console.log(item)
	   },
		 getStarting: function() {
	  	self=this;
      axios.get('/service/application',{port:8080})
  	.then(function (response) {
		for(var key in response.data) {
		  self.items.push(
				{   
					FirstName:  response.data[key].student.user.firstName,
					LastName:   response.data[key].student.user.lastName,
					InsaRanking: response.data[key].student.insaRanking,
					status: response.data[key].status,
					email:response.data[key].student.user.login,
					url:'<img  src="https://img.icons8.com/cotton/32/000000/checkmark.png">'
						+'<img src="https://img.icons8.com/dusk/32/000000/delete-sign.png">'
				})
		}
		self.showloader=false;
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