var app = new Vue({
  el: '#login',
  data: {
    message: '',
    firstName: '',
    lastName: '',
    userEmail: '',
    userPassword: '',
    selected: '',
    selectedUniv: '',
    successSignUp: '',
    endpoint: '',
    univs: [],
    idUserCreated: ''
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

      //TODO: FACTORIZE AXIOS FUNCTION

      axios
        .put('http://localhost:8080/service/user', json)
        .then(function (response) {
          obj = JSON.parse(JSON.stringify(response.data));
          if (obj.success == "false") {
            vue.successSignUp = "Email already used !";
          }
          else {
            var idUniversity = vue.getIdByURL(vue.selectedUniv);

            if (vue.selected === 'Student') {
              vue.putUserStudent(obj.id);
            }
            if (vue.selected === 'University') {
              vue.putUserUniversity(obj.id, idUniversity);
            }

            //sign up success : redirection to the login page
            document.location.href = "/login";
          }
        })
        .catch(function (error) {
          console.log(error);
        })
    },
    putUserUniversity: function (id, idUniversity) {
      var jsonUserUniv = {
        idUser: id,
        idUniv: idUniversity
      };
      axios
        .put('http://localhost:8080/service/userUniversity', jsonUserUniv)
        .then(function (response) {
          obj = JSON.parse(JSON.stringify(response.data));
          console.log(obj.success);
        })
        .catch(function (error) {
          console.log(error);
        })
    },
    putUserStudent: function (id) {
      var jsonUserStud = {
        idUser: id
      };

      axios
        .put('http://localhost:8080/service/userStudent', jsonUserStud)
        .then(function (response) {
          obj = JSON.parse(JSON.stringify(response.data));
          console.log(obj.success);
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
          for (i = 0; i < obj.length; i++) {
            vue.univs.push(obj[i]);
          }
        })
        .catch(function (error) {
          console.log(error);
        })
    },
    getIdByURL: function (url) {
      var i;
      for (i = 0; i < this.univs.length; i++) {
        if (this.univs[i]["url"] == url) {
          return this.univs[i]["id"];
        }
      }
      return null;
    }
  },
  beforeMount() {
    this.getUniversities();
    ;
  }
})