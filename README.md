# Astar

 La recherche d’un chemin avec un cout minimal vers une destination en utilisant l’algorithme A*. 
 
## 1. Problématique
 
 Dans des applications comme les jeux vidéo, la vitesse de calcul est bien plus privilégiée sur l'exactitude des résultats. Pour cela, le développeur de l’application doit trouver un algorithme à implémenter qui est performant et qui calcule de façon plus rapide le meilleur chemin vers une destination donnée. 
Pour cela, nous nous intéressons à l’algorithme A*, qui est une extension de l'algorithme de Dijkstra et qui  a été créé pour que la première solution trouvée soit l'une des meilleures. 

## 2. Objectif du projet
 
 L’objectif de ce projet est d’implémenter l’algorithme A* pour rechercher un chemin dans un graphe entre un nœud initial de départ et un nœud final comme destination, tous deux données. La solution trouvée par cet algorithme doit être l’une des meilleures, ainsi que rapide. 
 
## 3. Principe de l’algorithme A*  

 A chaque itération, nous allons tenter de se rapprocher de la destination, on va donc privilégier les possibilités directement plus proches de la destination, en mettant de côté toutes les autres. 
Toutes les possibilités ne permettant pas de se rapprocher de la destination sont mises de côté, mais pas supprimées. 

Elles sont simplement mises dans une liste de possibilités à explorer si jamais la solution explorée actuellement s'avère mauvaise. En effet, on ne peut pas savoir à l'avance si un chemin va aboutir ou sera le plus court. Il suffit que ce chemin amène à une impasse pour que cette solution devienne inexploitable. 
L'algorithme va donc d'abord se diriger vers les chemins les plus directs. 

Et si ces chemins n'aboutissent pas ou bien s'avèrent mauvais par la suite, il examinera les solutions mises de côté. C'est ce retour en arrière pour 
examiner les solutions mises de côté qui nous garantit que l'algorithme nous trouvera toujours une solution (si tenté qu'elle existe, bien sûr). 
On peut donc lui donner un terrain avec autant d'obstacles qu'on veut, aussi tordus soient-ils, s'il y a une solution, A* la trouvera. 

Pour éviter de développer des chemins estimés trop cher par rapport à ceux connus, A* fait référence à une fonction d’évaluation de son coût total. 

La fonction f pour un sommet n est : F(n) = h(n) + g(n) 
avec  
    h(n) : une heuristique de cout estimé pour aller depuis le nœud courant vers un nœud final. g(n) : le coût depuis la source vers le nœud courant. 

## 4. Réalisation du projet 

Dans notre projet, nous avons créé un programme qui fait la recherche du meilleur chemin depuis un nœud de départ vers un nœud final de destination d’une grille, ceci en implémentant l’algorithme A*. Cette grille peut aussi contenir des nœuds obstacles et l’algorithme les prend en considération durant le calcul de la solution. 
Une fois l’application lancée, L’utilisateur peut choisir la taille de la grille, les 2 dimensions de la grille peut aller de 5 jusqu’à 25 nœuds maximum. 
 
Nous avons implémenté 3 modes de fonctionnement pour notre application : Le mode Empty, Auto et dynamique. 

###    a) Le mode Empty 
Dans ce mode l’utilisateur obtient une grille vide qui ne contient aucun nœud obstacle, et ensuite il peut : - Désigner/supprimer un nœud comme obstacle en effectuant un clic droit sur le nœud.  - Désigner un nœud final de destination en effectuant un clic gauche sur ce nœud. L’utilisateur peut même changer de destination à n’importe quel moment, et l’algorithme répond à ce changement en prenant en compte le nœud courant comme nœud initial de départ.  
 
###    b) Le mode Auto 
Dans ce mode, nous avons les mêmes fonctionnalités qui existent dans le mode Empty, sauf qu’ici le programme fixe dès le début des nœuds obstacles aléatoirement dans la grille, et l’utilisateur peut modifier ces nœuds comme il désire de la même façon qu’en mode Empty. Le nœud final. 

###    c) Le mode dynamique   
En mode dynamique, les nœuds obstacles sont choisi par le programme et changent périodiquement chaque 5 seconde. L’utilisateur choisit le nœud final de destination et l’algorithme implémenté fait le calcul du meilleur chemin en prenant en compte tous ces changements. 
