### Conceitos/classes a fazer
- [ ] Product 
    - atributos: 
            - String id (case insensitive)
            - Batch
            - Price
            - Batch
            - has-a Recipe (que pode ser "nula")
            - 
-   -   Métodos:



- [ ] Transaction
-   -   Atributos:
            - int id;
            - ? data de pagamento
            - Partner partner
            - int ProductQuantity 
-   -   Métodos:

- [ ]  SellTransaction (is-a Transaction)
-   -   Atributos:
            - Batch[] batch
            - Product product
            - int n ()

- [ ]  BuyTransaction (is-a Transaction)
-   -   Atributos:
            - Batch batch


- [ ] BreakdownTransaction (is-a Transaction)
-   -   Atributos:
            - Product (caso a receita seja nula nada acontece)
            - Batch[] batches (where products come from)




- [ ] ManufactureTransaction (is-a Transacation)
    - Atributos:
        - Receita
-   -   Métodos:

- [ ] Recipe
-   -   Atributos:
        - lista de componentes
        - agravamento multiplicativo
        - has-a (0 a muitos) Products
-   -   Métodos:

- [ ] Batch 
-   -   Atributos:
        - id
        - provider
        - nº of available product units
        - product type
        - Transaction
        - Price per unit
-   -   Métodos:

- [ ] Partner 
-   -   Atributos:
        - Batch[]
        - Transaction(history)[]
        - String id (case insensitive)
        - String status (Elite, Selection, Normal)
        - String address
        - int pontos


- [ ] ElitePartner (is-a Partner)
- [ ] NormalPartner(is-a Partner)
- [ ] SelectionPartner(is-a Partner)
-   -   Métodos:


- [ ] Warehouse
-   -   Métodos:

- [ ] WarehouseManager
-   -   Métodos:

- [ ] Notification
-   -   Atributos:
        - Partner partner
        - String productId
        - bool Enabled
        - string description (ou "BARGAIN" ou "NEW")
        - deliveryMethod




### Qs Core:
- [ ] Lotes podem ser vendidos/comprados parcialmente?