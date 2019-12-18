// JavaScript source code
// /var/www/html/sites/emse/js/dom_api.js
// Création d'une requête HTTP 
/*var req = new XMLHttpRequest();
 // Requête HTTP GET synchrone vers l'api' publiée 
 req.open("GET", "https://thawing-journey-78988.herokuapp.com/api/rooms"); 
 req.addEventListener("load", function () {
if (req.status >= 200 && req.status < 400) { // Le serveur a réussi à traiter la requête         
	console.log(req.responseText);     
}
 else {         
 // Affichage des informations sur l'échec du traitement de la requête         
 console.error(req.status + " " + req.statusText);     
 } 
 });
 req.addEventListener("error", function () {  
 // La requête n'a pas réussi à atteindre le serveur
 console.error("Erreur réseau"); 
 }); 
 req.send(null);

// affiche la reponse en console 
function afficher(reponse) {    
	console.log(reponse); 
} */

// Exécute un appel AJAX GET // Prend en paramètres l'URL cible et la fonction callback appelée en cas de succès 
function ajaxGet(url, callback) {
    var req = new XMLHttpRequest(); 
	req.open("GET", url);  
	req.addEventListener("load", function () {   
	if (req.status >= 200 && req.status < 400) {         
	// Appelle la fonction callback en lui passant la réponse de la requête            
	callback(req.responseText);        
	} else {            
	console.error(req.status + " " + req.statusText + " " + url);       
	}   
	});    
	req.addEventListener("error", function () {  
	console.error("Erreur réseau avec l'URL " + url);   
	});    
	req.send(null); }

function getPartie(i,nom,rooms){
		var partie=document.createElement("p");
		partie.textContent=nom;
		var partieId="id: "+rooms[i][nom].id;
		var partieLevel="level: "+rooms[i][nom].level;
		var partieStatus="status: "+rooms[i][nom].status;
		var partieList=document.createElement("ul");
		var partieIdLi=document.createElement("li");
		var partieLevelLi=document.createElement("li");
		var partieStatusLi=document.createElement("li");
		partieIdLi.textContent=partieId;
		partieLevelLi.textContent=partieLevel;
		partieStatusLi.textContent=partieStatus;
		partieList.appendChild(partieIdLi);
		partieList.appendChild(partieLevelLi);
		partieList.appendChild(partieStatusLi);
		partie.appendChild(partieList);
		return partie;

}


function parseJSON(text){
	rooms=JSON.parse(text);
	var divrooms=document.getElementById("rooms");
	for (var i=0;i<rooms.length;i++){
		var room=document.createElement("h1");
		room.textContent="room "+rooms[i].id;
		var light=getPartie(i,"light",rooms,room);
		var noise=getPartie(i,"noise",rooms,room);
		room.appendChild(light);
		room.appendChild(noise);
		divrooms.appendChild(room);
	
	}

}

function switchLight(id){
var req = new XMLHttpRequest();
 req.open("GET", "https://thawing-journey-78988.herokuapp.com/api/rooms");
 var jsonArray=JSON.parse(req.response);
 var lightStatus=jsonArray[id].light.status;
 if (lightStatus==="OFF"){
 	 lightStatus="ON";
 }
 else{
 	 lightStatus="OFF";
 }
 req.send(lightStatus);

}



//ajaxGet("https://thawing-journey-78988.herokuapp.com/api/rooms",parseJSON)

//const heroku_url_api='https://thawing-journey-78988.herokuapp.com/api/rooms';

const heroku_url_api='http://127.0.0.1:8080/api/lights';

new Vue({
  el: '#rooms',
  data () {
    return {
      info: null,
      loading: true,
      errored: false,
	  selectedRoom:0
	  }

  },
  mounted () {
    axios
      .get(heroku_url_api)
      .then(response => (this.info = response.data))
	  .catch(error => {
        console.log(error)
        this.errored = true
      })
      .finally(() => this.loading = false)
	  
  },
  methods: {
   switchLight: function(room){  
   this.selectedRoom=room;
   let post_url=heroku_url_api+'/'+light.id+'/switch';
   axios.put(post_url,{roomId: room.id})
   .then(response =>{this.info=response.data})
}
  }
})
	

