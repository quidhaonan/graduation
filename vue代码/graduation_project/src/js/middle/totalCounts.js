import { $get } from '../../utils/request'


export async function initTotalCounts(productTotalCountsDom, shopTotalCountsDom) {
    let totalCounts = await $get('/middle/totalCounts')
    productTotalCountsDom.innerHTML = totalCounts[0].totalCounts
    shopTotalCountsDom.innerHTML = totalCounts[1].totalCounts
}