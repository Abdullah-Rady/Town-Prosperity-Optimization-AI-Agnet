:- include('KB.pl').



valid_plan(Plan, F, M, E) :-
    apply_plan(Plan, F, M, E, false, false).
   

apply_plan(s0, F, M, E, true, true) :- 
    food(F), materials(M), energy(E).

% build1 action
apply_plan(result(b1, S), Food, Materials, Energy, Build1, Build2) :-
    Build1 == false,
    apply_plan(S, NewFood, NewMaterials, NewEnergy, true, Build2),
    build1(F1, M1, E1),
    NewFood >= F1,
    NewMaterials >= M1,
    NewEnergy >= E1,
    Food is NewFood - F1,
    Materials is NewMaterials - M1,
    Energy is NewEnergy - E1.

% build2 action
apply_plan(result(b2, S), Food, Materials, Energy, Build1, Build2) :-
    Build2 == false,
    apply_plan(S, NewFood, NewMaterials, NewEnergy, Build1, true),
    build2(F1, M1, E1),
    NewFood >= F1,
    NewMaterials >= M1,
    NewEnergy >= E1,
    Food is NewFood - F1,
    Materials is NewMaterials - M1,
    Energy is NewEnergy - E1.

% request food action 
apply_plan(result(reqf, S), Food, Materials, Energy, Build1, Build2) :-
    apply_plan(S, NewFood, Materials, Energy, Build1, Build2),
    Food is NewFood + 1.

% request material action 
apply_plan(result(reqm, S), Food, Materials, Energy, Build1, Build2) :-
    apply_plan(S, Food, NewMaterials, Energy, Build1, Build2),
    Materials is NewMaterials + 1.

% request energy action 
apply_plan(result(reqe, S), Food, Materials, Energy, Build1, Build2) :-
    apply_plan(S, Food, Materials, NewEnergy, Build1, Build2),
    Energy is NewEnergy + 1.


% main query
goal(Plan) :-
    ids(Plan, 20).
  

ids(Plan, L):-
	(call_with_depth_limit(valid_plan(Plan, _, _, _), L, R), number(R));
	(call_with_depth_limit(valid_plan(Plan, _, _, _), L, R), R=depth_limit_exceeded,
	L1 is L+1, ids(Plan, L1)).