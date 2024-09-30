<!-- Footer Widget Secton -->
<footer class="site-footer">
<div class="container">
		<?php
			if ( is_active_sidebar( 'footer-widget-area' ) )
			{
			?>
		<div class="row footer-sidebar">
		<?php
			dynamic_sidebar( 'footer-widget-area' );
			 ?>
		</div>
			<?php } ?>
<?php
	$quality_current_options = wp_parse_args(  get_option( 'quality_pro_options', array() ), quality_theme_data_setup() ); ?>
<div class="row">
		<div class="col-md-12">
			<?php if($quality_current_options['footer_copyright_text']!='') { ?>
			<div class="site-info">
				<?php echo wp_kses_post($quality_current_options['footer_copyright_text']); ?>
			</div>
		<?php } ?>
		</div>
</div>
</div>
</footer>
	<!-- /Footer Widget Secton -->
</div>
<!------  Google Analytics code end ------->
</div> <!-- end of wrapper -->

<!-- Page scroll top -->
<a href="#" class="scroll-up"><i class="fa fa-chevron-up"></i></a>
<!-- Page scroll top -->

<?php

 wp_footer(); ?>
</body>
</html>
