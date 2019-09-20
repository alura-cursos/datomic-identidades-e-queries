(ns ecommerce.aula2
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [ecommerce.db :as db]
            [ecommerce.model :as model]))


(def conn (db/abre-conexao))

(db/cria-schema conn)

(def computador (model/novo-produto (model/uuid) "Computador Novo", "/computador-novo", 2500.10M))
(def celular (model/novo-produto (model/uuid) "Celular Caro", "/celular", 888888.10M))
(def calculadora {:produto/nome "Calculadora com 4 operações"})
(def celular-barato (model/novo-produto "Celular Barato", "/celular-barato", 0.1M))

(pprint @(d/transact conn [computador, celular, calculadora, celular-barato])) )

(def produtos (db/todos-os-produtos (d/db conn)))
(pprint produtos)

;  no momento que uso um identificador igual a algo que já existe não é uma nova entidade
; é uma atualização da existente
(def celular-barato-2 (model/novo-produto (:produto/id celular-barato) "CELULAR BARATO!!!!!", "/celular-baratissimo", 0.0001M))
(pprint celular-barato-2)
(pprint celular-barato)
(pprint @(d/transact conn [celular-barato-2]))

(def produtos (db/todos-os-produtos (d/db conn)))
(pprint produtos)


(db/apaga-banco)


