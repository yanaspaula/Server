new Vue({
  el: '#lights2',
  data () {
    return {
      info: null,
      loading: true,
      errored: false,
      champroomID: '',
	  selectedLight:0
	  }
  },

  mounted () {
    axios
      .get(api)
      .then(response => (this.info = response.data))
	  .catch(error => {
        console.log(error)
        this.errored = true
        if (this.errored===true){alert("Pas de réponse du serveur")}
      })
      .finally(() => this.loading = false)
  },

  methods: {


    setRoom: function(light){
        this.selectedLight=light;
        var roomId=this.champroomID;
        let post_url=api+'/'+light.id+'/set/'+this.champroomID;
        axios.put(post_url);
        axios
            .get(api)
            .then(response => (this.info = response.data))
    	      .catch(error => {
            console.log(error)
            this.errored = true
            if (this.errored===true){
              alert("Error")}
          })
          .finally(() => this.loading = false)
        },
  }
})

