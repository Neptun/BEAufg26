nonStop(T) :- type(T, 'trip'), formalParameterValue(from, X), formalParameterValue(to, Y), direct(X,Y).

type(T, 'trip') :- type(T, 'trainRide').
type(T, 'trip') :- type(T, 'flight').

type(X, Y) :- returnType(X, Y).