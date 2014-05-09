SparqlTypeIdentifyer
====================

Use case: you have a list of string representations of entities of one kind and want to receive its most likely rdf:type.
This project will get the most likely common classes for your list via the dbpedia endpoint.

Example input:

"Apple Inc."
"Microsoft"
"Asea Brown Boveri"
"Avex Group"
"Beiersdorf AG"
"British Steel"


Example output:

http://dbpedia.org/ontology/Agent  5
http://dbpedia.org/ontology/Company  5
http://dbpedia.org/ontology/Organisation  5
http://schema.org/Organization  5
http://www.w3.org/2002/07/owl#Thing  5
http://dbpedia.org/class/yago/Abstraction100002137  4
http://dbpedia.org/class/yago/Company108058098  4
