(ns ecommerce.model)

(defn uuid []  (java.util.UUID/randomUUID))

(defn novo-produto
  ; gerar ids dinamicamente
  ([nome slug preco]
   (novo-produto (uuid) nome slug preco))
  ; usar ids que ja foram criados antes
  ([uuid nome slug preco]
   {:produto/id    uuid
    :produto/nome  nome
    :produto/slug  slug
    :produto/preco preco}))