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
		   var self=this;
		   axios.post('/service/application', {
			   "type" : "response", 
			   "idApplication" : item.appId, 
			   "response" : "OK" 
			  })
			  .then(function (response) {
				location.reload();
			    console.log(response);
			  })
			  .catch(function (error) {
			    console.log(error);
			  });
	   },
	   refuse:function(item){
		   axios.post('/service/application', {
			   "type" : "response", 
			   "idApplication" :item.appId , 
			   "response" : "NOK" 
			  })
			  .then(function (response) {
			    console.log(response);
			  })
			  .catch(function (error) {
			    console.log(error);
			  });
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
					appId:response.data[key].id
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