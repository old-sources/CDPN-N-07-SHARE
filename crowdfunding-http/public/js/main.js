$(document).ready(function() {
    if(self.fetch) {
        var myHeaders = new Headers({"Content-Type": "application/json"});

        var myInit = {
            method: 'GET',
            headers: myHeaders
        };
        var projectListCreator = (json) => {

            var project = $("#projects")
            var template = $("#templates div#project_card")

            json.forEach(function(elem) {
                var card = template.clone();
                $("span.card-title", card).text(elem.nom)
                $("p", card).text(elem.description)
                $("div.card-action", card).text(`Objectif: ${elem.objectif}$`)
                card.appendTo(project);
            });

        };

        fetch('http://localhost:8080/crowdfunding-api/api/1/projet',myInit)
            .then((response) => {
                return response.json();
            }).then(projectListCreator)
            // .catch((err) => {
            //     alert('0')
            // })
        // exécuter ma requête fetch ici
    }
});
