const api='/api/lights';

new Vue({
  el: '#lights',
  data () {
    return {
      info: null,
      loading: true,
      errored: false,
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
  
  switchLight: function(light){
    this.selectedLight=light;
    let post_url=api+'/'+light.id+'/switch';
    axios.put(post_url);
  
    /*   .then(response => (this.info=response.data))
    .catch(error => {
           console.log(error)
           this.errored = true
           if (this.errored===true){alert("Error")}
         })*/
        
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
  
  getLightString(light){
    var colour=light.colour;
    var colourString;

    if (colour<21845){
      colourString="red"
    }

    else if (colour>=21845 && colour<43690){
      colourString="green"
    }

    else if (colour<65535){
      colourString="blue"
    }
    
    else {
      colourString="red"
    }

    return colourString;
  },
    
  setLight: function(colour,light){
    this.selectedLight=light;
    let put_url=api+'/'+light.id+'/set-light-'+colour;
    axios.put(put_url);
    axios
      .get(api)
      .then(response => (this.info = response.data))
    	.catch(error => {
        console.log(error)
        this.errored = true
            if (this.errored===true){
              alert("Error")
            }
          })
      .finally(() => this.loading = false)
    },
  }
})
	

