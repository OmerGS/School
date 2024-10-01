<?php
// Template Name: Quality-Page-Builder-Default

get_header();
?>
<div class="clearfix"></div>
<section id="section-block" class="site-content">
	<div class="container">
		<div class="row">	
			<div class="col-md-12 col-sm-8 col-xs-12">
				<div class="news">
					<article class="post">								
						<div class="post-content">	
							<?php 
							the_post(); 
							the_content(); ?>
						</div>				
					</article>
				</div>
			</div>
		</div>			
	</div>
</section>	
<?php 
get_footer();