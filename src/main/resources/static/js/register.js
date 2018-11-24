var app = new Vue({
  el: '#login',
  data: {
    message: '',
    firstName: '',
    lastName: '',
    userEmail: '',
    userPassword: '',
    selected: '',
    successSignUp: '',
    endpoint: '',
    univs: []
  },
  methods: {
    register: function () {
      var idRole;
      if (this.selected === 'Student') {
        idRole = 11;
      }
      if (this.selected === 'University') {
        idRole = 21;
      }
      if (this.selected === 'INSA') {
        idRole = 31;
      }
      console.log(this.lastName);
      var json = {
        lastName: this.lastName,
        firstName: this.firstName,
        login: this.userEmail,
        password: this.userPassword,
        enabled: 1,
        role: {
          "id": idRole
        }
      }
      var vue = this;
      console.log(json);

      axios
        .put('http://localhost:8080/service/signup', json)
        .then(function (response) {
          obj = JSON.parse(JSON.stringify(response.data));
          if (obj.success == "false") {
            vue.successSignUp = "Email already used !";
          }
          else {
            alert("ok ça marche !");
            document.location.href = "/login";
          }
        })
        .catch(function (error) {
          console.log(error);
        })
    },
    getUniversities: function () {
    	 var vue = this;
      axios
        .get('http://localhost:8080/service/university')
        .then(function (response) {
          obj = JSON.parse(JSON.stringify(response.data));
          var i;
          for(i=0;i<obj.length;i++){
              vue.univs.push(obj[i].url);
          }
        })
        .catch(function (error) {
          console.log(error);
        })
    }
  },
  beforeMount(){
	  this.getUniversities();
  }
})