export const toMoney = (n) => {
    return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.');
};
