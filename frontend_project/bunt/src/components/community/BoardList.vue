<script setup>

</script>

<template>
  <div class="container-fluid">
    <div _ngcontent-dmv-c12="" class="card mb-4">
      <div _ngcontent-dmv-c20="" class="card-header">
        DataTable Example
      </div>
      <div _ngcontent-dmv-c20="" class="card-body">
        <sb-ng-bootstrap-table _ngcontent-dmv-c20="" _nghost-dmv-c15="">
          <form _ngcontent-dmv-c15="" novalidate="" class="ng-untouched ng-pristine ng-valid">
            <div _ngcontent-dmv-c15="" class="form-group form-inline">Full text search: <input _ngcontent-dmv-c15=""
                                                                                               class="form-control ml-2 ng-untouched ng-pristine ng-valid"
                                                                                               name="searchTerm"
                                                                                               type="text"><!----></div>
            <table _ngcontent-dmv-c15="" class="table table-striped">
              <thead _ngcontent-dmv-c15="">
              <tr _ngcontent-dmv-c15="">
                <th _ngcontent-dmv-c15="" scope="col">#</th>
                <th _ngcontent-dmv-c15="" sbsortable="name" scope="col"><span _ngcontent-dmv-c15="">Country</span>
                  <!----></th>
                <th _ngcontent-dmv-c15="" sbsortable="area" scope="col"><span _ngcontent-dmv-c15="">Area</span><!---->
                </th>
                <th _ngcontent-dmv-c15="" sbsortable="population" scope="col"><span
                    _ngcontent-dmv-c15="">Population</span><!----></th>
              </tr>
              </thead>
              <tbody _ngcontent-dmv-c15=""><!---->
              <tr _ngcontent-dmv-c15="">
                <th _ngcontent-dmv-c15="" scope="row">1</th>
                <td _ngcontent-dmv-c15=""><img _ngcontent-dmv-c15="" class="mr-2" style="width: 20px"
                                               src="https://upload.wikimedia.org/wikipedia/commons/f/f3/Flag_of_Russia.svg">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->Russia<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->17,075,200<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->146,989,754<!----></ngb-highlight>
                </td>
              </tr>
              <tr _ngcontent-dmv-c15="">
                <th _ngcontent-dmv-c15="" scope="row">2</th>
                <td _ngcontent-dmv-c15=""><img _ngcontent-dmv-c15="" class="mr-2" style="width: 20px"
                                               src="https://upload.wikimedia.org/wikipedia/commons/c/c3/Flag_of_France.svg">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->France<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->640,679<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->64,979,548<!----></ngb-highlight>
                </td>
              </tr>
              <tr _ngcontent-dmv-c15="">
                <th _ngcontent-dmv-c15="" scope="row">3</th>
                <td _ngcontent-dmv-c15=""><img _ngcontent-dmv-c15="" class="mr-2" style="width: 20px"
                                               src="https://upload.wikimedia.org/wikipedia/commons/b/ba/Flag_of_Germany.svg">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->Germany<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->357,114<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->82,114,224<!----></ngb-highlight>
                </td>
              </tr>
              <tr _ngcontent-dmv-c15="">
                <th _ngcontent-dmv-c15="" scope="row">4</th>
                <td _ngcontent-dmv-c15=""><img _ngcontent-dmv-c15="" class="mr-2" style="width: 20px"
                                               src="https://upload.wikimedia.org/wikipedia/commons/5/5c/Flag_of_Portugal.svg">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->Portugal<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->92,090<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->10,329,506<!----></ngb-highlight>
                </td>
              </tr>
              <tr _ngcontent-dmv-c15="">
                <th _ngcontent-dmv-c15="" scope="row">5</th>
                <td _ngcontent-dmv-c15=""><img _ngcontent-dmv-c15="" class="mr-2" style="width: 20px"
                                               src="https://upload.wikimedia.org/wikipedia/commons/c/cf/Flag_of_Canada.svg">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->Canada<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->9,976,140<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->36,624,199<!----></ngb-highlight>
                </td>
              </tr>
              <tr _ngcontent-dmv-c15="">
                <th _ngcontent-dmv-c15="" scope="row">6</th>
                <td _ngcontent-dmv-c15=""><img _ngcontent-dmv-c15="" class="mr-2" style="width: 20px"
                                               src="https://upload.wikimedia.org/wikipedia/commons/2/21/Flag_of_Vietnam.svg">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->Vietnam<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->331,212<!----></ngb-highlight>
                </td>
                <td _ngcontent-dmv-c15="">
                  <ngb-highlight _ngcontent-dmv-c15=""><!----><!---->95,540,800<!----></ngb-highlight>
                </td>
              </tr>
              </tbody>
            </table>
            <div _ngcontent-dmv-c15="" class="d-flex justify-content-between p-2">
              <ngb-pagination _ngcontent-dmv-c15="" role="navigation"><!----><!----><!----><!----><!----><!---->
                <ul class="pagination"><!----><!---->
                  <li class="page-item disabled"><a aria-label="Previous" class="page-link" href="" tabindex="-1"
                                                    aria-disabled="true"><!----><span aria-hidden="true">«</span></a>
                  </li><!---->
                  <li class="page-item active" aria-current="page"><!----><!----><a class="page-link" href=""><!----> 1
                    <!----><span class="sr-only">(current)</span><!----></a></li>
                  <li class="page-item"><!----><!----><a class="page-link" href=""><!----> 2 <!----><!----></a></li>
                  <li class="page-item"><!----><!----><a class="page-link" href=""><!----> 3 <!----><!----></a></li>
                  <!---->
                  <li class="page-item"><a aria-label="Next" class="page-link" href=""><!----><span
                      aria-hidden="true">»</span></a></li><!----></ul>
              </ngb-pagination>
              <select _ngcontent-dmv-c15="" class="custom-select ng-untouched ng-pristine ng-valid" name="pageSize"
                      style="width: auto">
                <option _ngcontent-dmv-c15="" value="0: 2">2 items per page</option>
                <option _ngcontent-dmv-c15="" value="1: 4">4 items per page</option>
                <option _ngcontent-dmv-c15="" value="2: 6">6 items per page</option>
              </select></div>
          </form>
        </sb-ng-bootstrap-table>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>