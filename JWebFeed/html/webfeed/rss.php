<?php
/**
 * Statischer RSS-Feed. Die Beiträge stammen aus einem Array
 * und werden per Iteration in den Feed geschrieben.
 */

header('Content-Type: application/rss+xml; charset=UTF-8');

$title       = 'Mein RSS-Feed';
$link        = 'https://example.com';
$description = 'Ein statischer RSS-Feed';

// Datenquelle: hier könnten die Einträge später auch aus einer
// Datenbank oder Datei stammen. Aktuell genügt ein Beitrag.
$posts = [
    [
        'title'       => 'Hallo RSS',
        'description' => 'Hallo RSS',
        'link'        => $link . '/hallo-rss',
        'pubDate'     => date(DATE_RSS),
    ],
];

echo '<?xml version="1.0" encoding="UTF-8"?>' . "\n";
?>
<rss version="2.0">
  <channel>
    <title><?php echo htmlspecialchars($title); ?></title>
    <link><?php echo htmlspecialchars($link); ?></link>
    <description><?php echo htmlspecialchars($description); ?></description>
    <lastBuildDate><?php echo date(DATE_RSS); ?></lastBuildDate>

    <?php foreach ($posts as $post): ?>
    <item>
      <title><?php echo htmlspecialchars($post['title']); ?></title>
      <link><?php echo htmlspecialchars($post['link']); ?></link>
      <guid><?php echo htmlspecialchars($post['link']); ?></guid>
      <pubDate><?php echo $post['pubDate']; ?></pubDate>
      <description><?php echo htmlspecialchars($post['description']); ?></description>
    </item>
    <?php endforeach; ?>
  </channel>
</rss>
