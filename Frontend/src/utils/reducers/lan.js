const viState = {
    lan: 'vi',
    cart: "Giỏ hàng", VPP:"VPP & học cụ",type: "Thể loại",login: "Đăng nhập",logout: "Đăng xuất", search: "Tìm kiếm",
    slogan: "Cách dễ nhất để tìm sách tốt nhất!", let: "Chọn sách ngay", author: "Tác giả",price: "Giá",bookResultTitle:"Các sách tìm được:"
    ,authorResultTitle:"Các tác giả liên quan:", sortBy: "Sắp xếp theo: ", priceFrom: "Giá từ: ", category: "Thể loại: "
};
const enState = {
    lan: 'en',
    cart: "Cart", VPP:"Stationery & tools",type: "Type",login: "Login",logout: "Logout", search: "Search",
    slogan: "The easiest way to find the best books!", let: "Choose a book now", author: "Author",price: "Price",bookResultTitle:"Books found:"
    ,authorResultTitle:"Related Authors:", sortby: "Sorted by: ", priceFrom: "Price from: ", category: "Category: "
};

const lanReducer = (state = viState, action) => {
    switch (action.type) {
        case 'TO_VI': {
            return viState;
        }
        case 'TO_EN': {
            return enState;
        }
        default:
            return viState;
    }
};
export default lanReducer;
