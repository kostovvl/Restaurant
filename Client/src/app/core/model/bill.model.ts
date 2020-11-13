export default interface Bill{
    id: number;
    wiaterId: number;
    tableId: number;
    tableNumber: number;
    products: Map<number, number>;
    productPrices: Map<number, number>;
    totalPrice: number;
}