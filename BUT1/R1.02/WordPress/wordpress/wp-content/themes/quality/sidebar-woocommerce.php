<?php
/**
 * side bar template
 *
 * @subpackage Webriti
 */
?>
<?php if ( is_active_sidebar( 'woocommerce' ) ): ?>
	<div class="col-md-4 col-sm-4 col-xs-12">
		<div class="sidebar" >
		<?php dynamic_sidebar( 'woocommerce' );	 ?>
		</div>
	</div>
<?php endif; ?> 