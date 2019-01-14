var example1 = new Vue({
  el: '#example-1',
  data: {
    items: []
  },
  methods: {
	getNotifs: function () {
    var vue = this;
    axios
      .get('/service/notification')
      .then(function (response) {
        obj = JSON.parse(JSON.stringify(response.data));
        console.log(obj);
        var i;
        for (i = 0; i < obj.length; i++) {
          vue.items.push(obj[i]);
        }
      })
      .catch(function (error) {
        console.log(error);
      })
  }
  },
  beforeMount() {
    this.getNotifs();
  }
})