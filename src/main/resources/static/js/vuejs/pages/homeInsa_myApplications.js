var app = new Vue({
   el: '#HomeInsa_myApplications',
   data: {
		 applications:"par default",
		 items:[],
		 note:[],
		 opened: [],
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
	   shownote:function(id){
		   	const index = this.opened.indexOf(id);
		      if (index > -1) {
		      	this.opened.splice(index, 1)
		      } else {
		      	this.opened.push(id)
		      }
		   
	   },
	   accept:function(item){
		   var self=this;
		   self.showloader=true;
		   console.log(item);
		   axios.post('/service/application', {
			   "type" : "response", 
			   "idApplication" : item.appId, 
			   "idUser":item.studentId,
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
		   self.showloader=true;
		   axios.post('/service/application', {
			   "type" : "response", 
			   "idApplication" :item.appId , 
			   "idUser":item.studentId,
			   "response" : "NOK" 
			  })
			  .then(function (response) {
			    console.log(response);
				location.reload();
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
  		console.log(response)
		for(var key in response.data) {
		  self.items.push(
				{   
					FirstName:  response.data[key].student.user.firstName,
					LastName:   response.data[key].student.user.lastName,
					InsaRanking: response.data[key].student.insaRanking,
					status: response.data[key].status,
					email:response.data[key].student.user.login,
					appId:response.data[key].id,
					studentId:response.data[key].student.id
				})
		}
  		 axios.get('/service/note',{port:8080})
  	  	.then(function (response) {
  	  		self.note=response.data;
  	  		console.log(self.note)
  	  	})
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