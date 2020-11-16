import ProductInfoDto from './product.info';

export default interface Bill{
    id: number;
    wiaterId: number;
    tableId: number;
    tableNumber: number;
    products: Map<number, ProductInfoDto>;
    totalPrice: number;
}