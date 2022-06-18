const searchBar = document.querySelector('#search-bar');
let products = document.querySelectorAll('.product');

searchBar.oninput = (e) => {
  let searchInput = searchBar.value.toLowerCase();

  products.forEach((product) => {
    let productName = product.textContent.toLowerCase();

    // Hide if not same
    if (!productName.includes(searchInput)) {
      product.style.display = 'none';
    } else {
      product.style.display = 'grid';
    }
  });
};

const addCartButtons = document.querySelectorAll('#add-cart');
addCartButtons.forEach((button) => {
  button.onclick = async (e) => {
    const form = button.closest('form');
    const cartActivity = form.querySelector('.cart-activity').value;
    const productId = form.querySelector('.product-id').value;
   
    await addToCart(cartActivity, productId);

    alert('Product added to cart');
  };
});

const buyButtons = document.querySelectorAll('#buy');
buyButtons.forEach((button) => {
  button.onclick = async (e) => {
    const form = button.closest('form');
    const cartActivity = form.querySelector('.cart-activity').value;
    const productId = form.querySelector('.product-id').value;

    await addToCart(cartActivity, productId);
    location.href = 'cart';
  };
});

async function addToCart(cartActivity, productId) {
  await fetch('cart', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: `cartActivity=${cartActivity}&productId=${productId}`,
  });

  return false;
}