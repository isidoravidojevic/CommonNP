package napredno.programiranje.communication;

import java.io.Serializable;

public enum Operation implements Serializable {
	LOGIN, LOGOUT,
    GET_ALL_PRODUCTS, ADD_PRODUCT, EDIT_PRODUCT, DELETE_PRODUCT,
    ADD_CUSTOMER, DELETE_CUSTOMER, GET_ALL_CUSTOMERS, EDIT_CUSTOMER,
    ADD_INVOICE, CANCEL_INVOICE, GET_ALL_INVOICES, GET_ALL_INVOICES_PARAMETER,
    ADD_INVOICE_ITEM, GET_ALL_INVOICE_ITEMS_PARAMETER, GET_ALL_INVOICE_ITEMS,
    ADD_INVOICE_RECEPTION_TYPE
}
